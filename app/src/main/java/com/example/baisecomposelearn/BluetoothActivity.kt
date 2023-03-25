package com.example.baisecomposelearn

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_ENABLE_BT = 1
        private val MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothDevice: BluetoothDevice
    private lateinit var bluetoothSocket: BluetoothSocket
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        setContent {
            BluetoothApp()
        }
    }

    @Composable
    fun BluetoothApp() {
        var deviceAddress by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = deviceAddress,
                onValueChange = { deviceAddress = it },
                label = { Text("Device Address") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { connectToDevice(deviceAddress) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Connect")
            }

            Button(
                onClick = { sendMessage(message) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Send")
            }
        }
    }

    private fun connectToDevice(deviceAddress: String) {
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(deviceAddress)

        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID)
            bluetoothSocket.connect()
            Log.d(TAG, "连接成功")

            inputStream = bluetoothSocket.inputStream
            outputStream = bluetoothSocket.outputStream
        } catch (e: IOException) {
            Log.e(TAG, "连接失败", e)
        }
    }

    private fun sendMessage(message: String) {
        val bytes = message.toByteArray()
        try {
            outputStream.write(bytes)
            Log.d(TAG, "消息发送成功：$message")
        } catch (e: IOException) {
            Log.e(TAG, "发送消息失败", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            inputStream.close()
            outputStream.close()
            bluetoothSocket.close()
        } catch (e: IOException) {
            Log.e(TAG, "关闭连接失败", e)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BluetoothApp()
    }
}