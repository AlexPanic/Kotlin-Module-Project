import java.util.Scanner
interface Listable {
    val name: String
}
class Menu(var screen: String) {

    var storage = mutableListOf<Archive>()
    var curArchiveIndex: Int = -1
    var curNoteIndex: Int = -1
    init {
        this.storage.add(Archive("A1", mutableListOf<Note>()))
    }
    fun render() {
        if (screen == Archive.Headers.EXIT.alias) {
            return
        }
        println("$screen:")
        when (screen) {
            Archive.Headers.LIST.alias -> {
                list(this.storage)
                getChoice(this.storage.size+1, Archive.Headers.NEW.alias, Note.Headers.LIST.alias, Archive.Headers.EXIT.alias)
            }
            Archive.Headers.NEW.alias -> {
                getNew()
            }
            Note.Headers.LIST.alias -> {
                list(this.storage[curArchiveIndex].notes)
                getChoice(this.storage[curArchiveIndex].notes.size+1, Note.Headers.NEW.alias, Note.Headers.SHOW.alias, Archive.Headers.LIST.alias)
            }
        }
    }

    fun getNew() {
        println("Введите название");
        val input = Scanner(System.`in`).nextLine().toString()
        if (input.isEmpty()) {
            if (Archive.contains(input, storage)) {
                println("Такой архив уже существует");
            } else {
                storage.add(Archive(input, mutableListOf<Note>()))
                screen = Archive.Headers.LIST.alias
            }
        }
        render()
    }

    fun getChoice(maxChoice:Int, newScreen:String, editScreen: String, backScreen: String) {
        val input = Scanner(System.`in`).nextLine().toIntOrNull()
        when (input) {
            0 -> screen = newScreen
            in 1..maxChoice-1 -> {
                curArchiveIndex = input!!-1
                screen = editScreen
            }
            maxChoice -> screen = backScreen
            null -> println("Нет такого пункта меню. Нужно ввести корректное число")
            else -> println("Нужно ввести число")
        }
        render()
    }

    fun <T:Listable>list(list: MutableList<T>) {
        println("0.Создать")
        for (k in list.indices) {
            println("${k+1}.${list[k].name}");
        }
        println("${list.size+1}.Выход")
    }

}