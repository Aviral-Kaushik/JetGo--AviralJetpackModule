package com.aviral.jetpackbasics.bestPractices.permissionHandling

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.aviral.jetpackbasics.bestPractices.ui.theme.JetpackBasicsTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

// implementation "com.google.accompanist:accompanist-permissions:0.21.1-beta"

@ExperimentalPermissionsApi
class PermissionHandling : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
//               For Single Permission Handling use
//               val permissionState = rememberPermissionState(permission = )

//               For Multiple Permission Handling Use
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_RESUME) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        }

                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    permissionState.permissions.forEach { perm ->

                        when (perm.permission) {

                            Manifest.permission.CAMERA -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Camera Permission Accepted")
                                    }

                                    perm.shouldShowRationale -> {
                                        Text(text = "Camera Permission is needed" +
                                                " to access the camera")
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Camera Permission is permanently denied. " +
                                                " You can enable it in the app settings.")
                                    }
                                }
                            }

                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Record Audio Permission Accepted")
                                    }

                                    perm.shouldShowRationale -> {
                                        Text(text = "Audio Permission is needed" +
                                                " to record the mic")
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Record Audio Permission is permanently denied. " +
                                                " You can enable it in the app settings.")
                                    }
                                }
                            }

                        }

                    }

                }

            }
        }
    }
}