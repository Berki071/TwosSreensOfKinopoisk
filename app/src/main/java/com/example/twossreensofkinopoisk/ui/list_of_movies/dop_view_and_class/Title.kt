package com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twossreensofkinopoisk.R

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier
) {

    val robotoFamily = FontFamily(
        Font(R.font.roboto_regular400, FontWeight.Normal),
        Font(R.font.roboto_medium500, FontWeight.Medium),
        Font(R.font.roboto_bold700, FontWeight.Bold)
    )

    Row(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun TitlePreview() {
    Title(title= "Мой заголовок")
}