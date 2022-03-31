package com.example.motionlayoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.Top
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.motionlayoutapp.ui.theme.MotionLayoutAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMotionApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutAppTheme {

                Column (
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ){
                    var progress by remember {
                        mutableStateOf(0f)
                    }
                    ProfileHeader(progress = progress)
                    Slider(
                        value = progress,
                        onValueChange = {
                            progress = it },
                        modifier = Modifier.padding(32.dp)
                    )
                }

            }
        }
    }
}

@ExperimentalMotionApi
@Composable
fun ProfileHeader(progress: Float) {
    
    val context = LocalContext.current
    
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    
        MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
        ) {

            val properties = motionProperties(id = "username_txt")
            
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "pizza",
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 4.dp,
                    color = Color.Cyan
                )
                .layoutId("pizza_pic")
        )
        Text(
            text = "Mito",
            color = properties.value.color("text_color"),
            fontSize = 60.sp,
            modifier = Modifier
                .layoutId("username_txt")
        )
    }
}

