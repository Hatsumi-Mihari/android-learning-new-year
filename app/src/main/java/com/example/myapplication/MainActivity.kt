package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.components.LayoutText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val versionSKD = Build.MANUFACTURER;
        val couterState = CounterViewModel();
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.audio);
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenLayout(couterState, mediaPlayer)
        }
    }
}

@Composable
fun MainScreenLayout(vm: CounterViewModel, mp: MediaPlayer){
    val state by vm.state.collectAsState();
    Box(
        modifier = Modifier.fillMaxSize().
        background(Color(30,30,30))
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp)
        ){
            Box(
                modifier = Modifier.wrapContentHeight()
                    .background(
                        Color(50, 50,50),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(300.dp)
                    .padding(15.dp)
            ){
                Column() {
                    if (state.count == 26){
                        LayoutText("POTATO 2026, HEPPY NEW YEAR!!!")
                        mp.setOnCompletionListener {
                            it.release()
                        }
                        mp.setVolume(0.5f, 0.5f);
                        mp.start();
                    }else{
                        LayoutText("Hello World")
                    }
                    LayoutText("Time From Start App: ${vm.getTimeFromStart()} ms")
                    LayoutText("Count: ${state.count}")
                    Button(onClick = {vm.increment()}){
                        Text("Click")
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            while(true) {
                delay(250)
                vm.forceUpdateTime();
            }
        }
    }
}