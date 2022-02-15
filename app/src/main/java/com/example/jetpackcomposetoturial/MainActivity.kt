package com.example.jetpackcomposetoturial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetoturial.ui.theme.JetpackComposeToturialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeToturialTheme {
//                MessageCard(msg = Message("Android", "Jetpack Compose")) 
                Conversation(messages = listOf(
                    Message("Android","Test...Test...Test"),
                    Message("Android","In this code snippet, you can see that LazyColumn has an items child. It takes a List as a parameter and its lambda receives a parameter we’ve named message (we could have named it whatever we want) which is an instance of Message"),
                    Message("Android","Test...Test...Test"),
                    Message("Android","Test...Test...Test"),
                    Message("Android","Test...Test...Test"),
                ))
            }
        }
    }
    
    data class Message(
        val author:String,
        val body: String
    )
    
    @Composable
    fun MessageCard(msg:Message) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        ){
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            var isExpanded by remember {
                mutableStateOf(false)
            }
            val surfaceColor : Color by animateColorAsState(
                if(isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )
            Column(modifier = Modifier.clickable { isExpanded=!isExpanded }){
                Text(text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1)
                Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2,
                        maxLines = if(isExpanded) Int.MAX_VALUE else 2
                    )
                }
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard() {
        JetpackComposeToturialTheme{
            MessageCard(msg = Message("Android","Jetpack Compose"))
        }
    }

    @Composable
    fun Conversation(messages : List<Message>){
        LazyColumn{
            items(messages){message->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        JetpackComposeToturialTheme {
            Conversation(listOf(
                Message("Android","Test...Test...Test"),
                Message("Android","In this code snippet, you can see that LazyColumn has an items child. It takes a List as a parameter and its lambda receives a parameter we’ve named message (we could have named it whatever we want) which is an instance of Message"),
                Message("Android","Test...Test...Test"),
                Message("Android","Test...Test...Test"),
                Message("Android","Test...Test...Test"),
            ))
        }
    }
}
