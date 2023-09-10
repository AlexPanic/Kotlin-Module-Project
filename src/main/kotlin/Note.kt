data class Note(override val name: String, val text: String?):Listable {
    enum class Headers(val alias: String) {
        LIST("Список заметок"),
        NEW("Новая заметка"),
        SHOW("Содержимое заметки")
    }
}
