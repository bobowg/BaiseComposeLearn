package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons


private val lists = listOf(
    FaIcons.AccessibleIcon,
    FaIcons.Accusoft,
    FaIcons.AcquisitionsIncorporated,
    FaIcons.Ad,
    FaIcons.AddressBook,
    FaIcons.AddressBookRegular,
    FaIcons.AddressCard,
    FaIcons.AddressCardRegular,
    FaIcons.Adjust,
    FaIcons.Adn,
    FaIcons.Adversal,
    FaIcons.Affiliatetheme,
    FaIcons.AirFreshener,
    FaIcons.Airbnb,
    FaIcons.Algolia,
    FaIcons.AlignCenter,
    FaIcons.AlignJustify,
    FaIcons.AlignLeft,
    FaIcons.AlignRight,
    FaIcons.Alipay,
    FaIcons.Allergies,
    FaIcons.Amazon,
    FaIcons.AmazonPay,
    FaIcons.Ambulance,
    FaIcons.AmericanSignLanguageInterpreting,
    FaIcons.Amilia,
    FaIcons.Anchor,
    FaIcons.Android,
    FaIcons.Angellist,
    FaIcons.AngleDoubleDown,
    FaIcons.AngleDoubleLeft,
    FaIcons.AngleDoubleRight,
    FaIcons.AngleDoubleUp,
    FaIcons.AngleDown,
    FaIcons.AngleLeft,
    FaIcons.AngleRight,
    FaIcons.AngleUp,
    FaIcons.Angry,
    FaIcons.AngryRegular,
    FaIcons.Angrycreative,
    FaIcons.Angular,
    FaIcons.Ankh,
    FaIcons.AppStore,
    FaIcons.AppStoreIos,
    FaIcons.Apper,
    FaIcons.Apple,
    FaIcons.AppleAlt,
    FaIcons.ApplePay,
    FaIcons.Archive,
    FaIcons.Archway,
    FaIcons.ArrowAltCircleDown,
    FaIcons.ArrowAltCircleDownRegular,
    FaIcons.ArrowAltCircleLeft,
    FaIcons.ArrowAltCircleLeftRegular,
    FaIcons.ArrowAltCircleRight,
    FaIcons.ArrowAltCircleRightRegular,
    FaIcons.ArrowAltCircleUp,
    FaIcons.ArrowAltCircleUpRegular,
    FaIcons.ArrowCircleDown,
    FaIcons.ArrowCircleLeft,
    FaIcons.ArrowCircleRight,
    FaIcons.ArrowCircleUp,
    FaIcons.ArrowDown,
    FaIcons.ArrowLeft,
    FaIcons.ArrowRight,
    FaIcons.ArrowUp,
    FaIcons.ArrowsAlt,
    FaIcons.ArrowsAltH,
    FaIcons.ArrowsAltV,
    FaIcons.Artstation,
    FaIcons.AssistiveListeningSystems,
    FaIcons.Asterisk,
    FaIcons.Asymmetrik,
    FaIcons.At,
    FaIcons.Atlas,
    FaIcons.Atlassian,
    FaIcons.Atom,
    FaIcons.Audible,
    FaIcons.AudioDescription,
    FaIcons.Autoprefixer,
    FaIcons.Avianex,
    FaIcons.Aviato,
    FaIcons.Award,
    FaIcons.Aws,
)


@Composable
fun FontAwesomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.fontawesomescreen)
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 60.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary)
            ) {
                items(lists) { list ->
                    FaIcon(
                        faIcon = list,
                        modifier = Modifier.padding(8.dp),
                        size = 36.dp,
                        tint = MaterialTheme.colors.surface
                    )
                }
            }
        }
    }


}

@Preview
@Composable
fun FontAwesomeScreenPreview() {
    FontAwesomeScreen(navController = rememberNavController())
}