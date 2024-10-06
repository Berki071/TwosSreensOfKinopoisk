package com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twossreensofkinopoisk.R

@Composable
fun GenreItem(
    item: GenreForList,
    selectedGenre: GenreForList?,
    onClick: (GenreForList)->Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier= modifier
        .background(if(selectedGenre == null || selectedGenre.title != item.title) Color.White else colorResource(R.color.purple_200))
        .fillMaxWidth()
        .clickable {
            onClick(item)
        }
    ){
        Text(
            text = item.title,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Preview
@Composable
private fun GenreItemPreview() {
    GenreItem(item= GenreForList("Text title", false),selectedGenre = null, onClick = {})
}