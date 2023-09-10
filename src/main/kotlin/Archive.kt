data class Archive(override val name: String, val notes: MutableList<Note>):Listable {
    enum class Headers(val alias: String) {
        EXIT("Выход"),
        LIST("Список архивов"),
        NEW("Новый архив")
    }

    companion object {
        fun contains(name: String, ar: MutableList<Archive>):Boolean {
            for (item in ar) {
                if (item.name == name) {
                    return true
                }
            }
            return false
        }
    }
}