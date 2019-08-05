package riflockle7.co.kr.components

data class Item(
    var name: String,
    var imageURL: String
)

fun getProgressItem(): Item {
    return Item(name = "progress", imageURL = "progress")

}