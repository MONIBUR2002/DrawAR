package com.moniapps.drawar.navigation

sealed class ScreenGraph (var route: String){
    data object MainScreen: ScreenGraph("Main_Screen")
    data object SelectPhotoScreen: ScreenGraph("Select_Photo_Screen")
    data object CameraScreen: ScreenGraph("Trace_On_Camera_Screen")
    data object TraceOnPhoneScreen: ScreenGraph("Trace_On_Phone_Screen")
}