data class Archive(override val name: String, val notes: MutableList<Note>):Listable {
    enum class Headers(val alias: String) {
        EXIT("Выход"),
        LIST("Список архивов"),
        NEW("Новый архив")
    }
    companion object {
        const val UNPICKED = -1
    }

}