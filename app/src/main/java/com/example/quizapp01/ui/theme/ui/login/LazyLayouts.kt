import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp01.ui.theme.data.Quiz

data class SchoolClass(val className: String)

@Composable
fun ClassList(classes: List<SchoolClass>) {
    LazyColumn {
        items(classes) { schoolClass ->
            Text(text = schoolClass.className)
        }
    }
}

@Preview
@Composable
fun ClassListPreview() {
    val classes = listOf(
        SchoolClass("Class 6"),
        SchoolClass("Class 7"),
        SchoolClass("Class 8"),
        SchoolClass("Class 9"),
        SchoolClass("Class 10")
    )

    ClassList(classes=classes)
}
