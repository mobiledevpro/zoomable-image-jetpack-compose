/*
 * Copyright 2023 | Dmitri Chernysh | https://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.image.viewer.view

import android.icu.text.CaseMap.Upper
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size

/**
 * Demo screen for zoomable image
 *
 * Created on Feb 18, 2024.
 *
 */

@Composable
fun ImageViewerScreen() {

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) { paddings ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    UpperImage()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LowerImage()
                }
            }

        }

    }


}

@Composable
fun BoxScope.UpperImage() {
    var imageScale by remember { mutableFloatStateOf(1f) }
    var imageOffset by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, panChange, rotationChange ->
        imageScale = (imageScale * zoomChange).coerceIn(1f, 5f)
        imageOffset += panChange
    }

    val model = ImageRequest.Builder(LocalContext.current)
        .data("https://c4.wallpaperflare.com/wallpaper/83/500/871/waterfall-high-resolution-desktop-wallpaper-preview.jpg")
        .size(Size.ORIGINAL) // Set the target size to load the image at.
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .diskCachePolicy(CachePolicy.DISABLED)
        .build()

    AsyncImage(
        model = model,
        contentDescription = "",
        contentScale = ContentScale.FillHeight,
        onState = { state ->
            Log.d("UI", "AsyncImage.state: $state")
        },
        modifier = Modifier
            .align(Alignment.Center)
            .fillMaxWidth()
            .scale(imageScale)
            .graphicsLayer {
                Log.d("UI", "ImageViewerScreen: graphicsLayer: scale $scaleX $scaleY")
                scaleX = imageScale
                scaleY = imageScale
                translationX = imageOffset.x
                translationY = imageOffset.y
            }
            .transformable(transformState)
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = {
                    Log.d("UI", "ImageViewerScreen: on double tap")
                    imageScale = 1f
                    imageOffset = Offset.Zero
                })
            }
    )
}

@Composable
fun BoxScope.LowerImage() {
    var imageScale by remember { mutableFloatStateOf(1f) }
    var imageOffset by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, panChange, rotationChange ->
        imageScale = (imageScale * zoomChange).coerceIn(1f, 5f)
        imageOffset += panChange
    }

    val model = ImageRequest.Builder(LocalContext.current)
        .data("https://c4.wallpaperflare.com/wallpaper/352/684/15/underwater-high-resolution-desktop-backgrounds-wallpaper-preview.jpg")
        .size(Size.ORIGINAL) // Set the target size to load the image at.
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .diskCachePolicy(CachePolicy.DISABLED)
        .build()

    AsyncImage(
        model = model,
        contentDescription = "",
        contentScale = ContentScale.FillHeight,
        onState = { state ->
            Log.d("UI", "AsyncImage.state: $state")
        },
        modifier = Modifier
            .align(Alignment.Center)
            .fillMaxWidth()
            .scale(imageScale)
            .graphicsLayer {
                Log.d("UI", "ImageViewerScreen: graphicsLayer: scale $scaleX $scaleY")
                scaleX = imageScale
                scaleY = imageScale
                translationX = imageOffset.x
                translationY = imageOffset.y
            }
            .transformable(transformState)
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = {
                    Log.d("UI", "ImageViewerScreen: on double tap")
                    imageScale = 1f
                    imageOffset = Offset.Zero
                })
            }
    )
}
