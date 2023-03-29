package com.brainjug.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brainjug.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                /*text styling with custom font, you can give different styles to each part of your text */
//                ShowTextStyleDemo()

                /*how state works in compose*/
//                ShowState()

                /*Snackbar example*/
//                ShowSnackbar()

                /*card view example with round corner, gradient color and box*/
//                ShowRoundedCard()

                /*constraint layout demo with constraints, guideline, chain style*/
//                ShowConstraintLayout()

                /*vertical scrollable list*/
//                ShowLazyList()

                /*Form with text fields and button , includes validation */
                AddPickupAddress()

                /*animation*/
//                ShowAnimationDemo()

                /*custom circular progress bar*/
//                ShowCustomCircularProgress()

                /*draggable Music Knob*/
//                ShowDraggableMusicKnob()
            }
        }
    }
}