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
        //storage.add(Archive("A1", mutableListOf<Note>()))
    }
    fun render() {
        while (true) {
            if (screen == Archive.Headers.EXIT.alias) {
                return
            }
            println("$screen:")
            when (screen) {
                Archive.Headers.LIST.alias -> {
                    Common.list(storage)
                    curArchiveIndex = Common.getPick(this, storage, Archive.Headers.NEW.alias, Note.Headers.LIST.alias, Archive.Headers.EXIT.alias)
                }
                Archive.Headers.NEW.alias -> {
                    storage.add(Archive(Common.getNew(storage), mutableListOf()))
                    screen = Archive.Headers.LIST.alias
                }
                Note.Headers.NEW.alias -> {
                    storage[curArchiveIndex].notes.add(Note(Common.getNew(storage[curArchiveIndex].notes), null))
                    curNoteIndex = storage[curArchiveIndex].notes.size-1
                    screen = Note.Headers.ADDTEXT.alias
                }
                Note.Headers.ADDTEXT.alias -> {
                    addText()
                }
                Note.Headers.LIST.alias -> {
                    Common.list(storage[curArchiveIndex].notes)
                    curNoteIndex = Common.getPick(this, storage[curArchiveIndex].notes, Note.Headers.NEW.alias, Note.Headers.SHOW.alias, Archive.Headers.LIST.alias)
                }
                Note.Headers.SHOW.alias -> {
                    println("${storage[curArchiveIndex].name}/${storage[curArchiveIndex].notes[curNoteIndex].name}")
                    println("${storage[curArchiveIndex].notes[curNoteIndex].text}")
                    println("(Введите любой символ чтобы вернуться к списку заметок)")
                    backToNotes()
                }
                else -> return println("Неизвестный экран приложения")
            }
        }
    }

    fun addText() {
        println("(Введите текст или Enter чтобы отменить создание заметки)")
        val input = Scanner(System.`in`).nextLine().toString()
        if (input.isEmpty()) {
            screen = Note.Headers.LIST.alias
            storage[curArchiveIndex].notes.removeAt(curNoteIndex)
        }
        else {
            storage[curArchiveIndex].notes[curNoteIndex].text = input
            screen = Note.Headers.SHOW.alias
        }
    }

    fun backToNotes() {
        while (true) {
            val input = Scanner(System.`in`).nextLine().toString()
            if (!input.isEmpty()) {
                screen = Note.Headers.LIST.alias
                return
            }
        }
    }

}