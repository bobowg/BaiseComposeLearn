package com.example.baisecomposelearn.screens.animate

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CurtainEffectScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {

    })
}

@Composable
fun Curtain(
    isOpenedFromOutside:Boolean? = null,
    foldingDuration:Int = 250,
    mainCell:@Composable () -> Unit,
    foldCells:List<@Composable ()-> Unit>
) {
    var isOpened by remember{ mutableStateOf(false)}
    var isTransitionRuning by remember{ mutableStateOf(false)}
    val foldScope = rememberCoroutineScope()

    fun toggleCurtain(){
        if (!isTransitionRuning){
            isTransitionRuning = true
            isOpened = !isOpened
            foldScope.launch {
                delay(foldingDuration.toLong() * foldCells.size)
                isTransitionRuning = false
            }
        }
    }
}