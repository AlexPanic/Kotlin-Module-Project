data class Note(override val name: String, var text: String?):Listable {
    enum class Headers(val alias: String) {
        LIST("Список заметок"),
        NEW("Новая заметка"),
        ADDTEXT("Добавить текст"),
        SHOW("Содержимое заметки")
    }
    companion object {
        const val UNPICKED = -1
    }
}
