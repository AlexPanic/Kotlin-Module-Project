import java.util.Scanner
interface Listable {
    val name: String
}
class Menu(var screen: String) {
    companion object {
        const val UNPICKED = -1
    }
    var storage = mutableListOf<Archive>()
    var curArchiveIndex: Int = UNPICKED
    var curNoteIndex: Int = UNPICKED
    init {
        storage.add(Archive("A1", mutableListOf<Note>()))
    }
    fun render() {
        while (true) {
            if (screen == Archive.Headers.EXIT.alias) {
                return
            }
            println("$screen:")
            when (screen) {
                Archive.Headers.LIST.alias -> {
                    list(storage)
                    curArchiveIndex = getPick(storage, Archive.Headers.NEW.alias, Note.Headers.LIST.alias, Archive.Headers.EXIT.alias)
                }
                Archive.Headers.NEW.alias -> {
                    storage.add(Archive(getNew(storage), mutableListOf()))
                    screen = Archive.Headers.LIST.alias
                }
                Note.Headers.NEW.alias -> {
                    storage[curArchiveIndex].notes.add(Note(getNew(storage[curArchiveIndex].notes), null))
                    screen = Note.Headers.LIST.alias
                }
                Note.Headers.LIST.alias -> {
                    list(storage[curArchiveIndex].notes)
                    curNoteIndex = getPick(storage[curArchiveIndex].notes, Note.Headers.NEW.alias, Note.Headers.SHOW.alias, Archive.Headers.LIST.alias)
                }
                else -> return println("Неизвестный экран приложения")
            }
        }
    }

    fun <T:Listable>contains(name: String, ar: MutableList<T>): Boolean {
        for (item in ar) {
            if (item.name == name) {
                return true
            }
        }
        return false
    }

    fun <T:Listable>getPick(list: MutableList<T>, newScreen:String, editScreen: String, backScreen: String):Int {
        while (true) {
            println("Введите номер нужного пункта меню");
            val input = Scanner(System.`in`).nextLine().toIntOrNull()
            when (input) {
                0 -> {
                    screen = newScreen
                    return UNPICKED
                }
                list.size+1 -> {
                    screen = backScreen
                    return UNPICKED
                }
                in 1..list.size -> {
                    screen = editScreen
                    return input!!-1
                }
                null -> println("Нет такого пункта меню. Нужно ввести корректное число")
                else -> println("Нужно ввести число")
            }
        }
    }
    fun <T:Listable>getNew(list: MutableList<T>):String {
        while (true) {
            println("Введите название");
            val input = Scanner(System.`in`).nextLine().toString()
            if (!input.isEmpty()) {
                if (contains(input, list)) {
                    println("[!] Элемент с таким названием уже существует");
                } else {
                    return input
                }
            }
        }
    }

    fun <T:Listable>list(list: MutableList<T>) {
        println("0.Создать")
        for (k in list.indices) {
            println("${k+1}.${list[k].name}");
        }
        println("${list.size+1}.Выход")
    }

}