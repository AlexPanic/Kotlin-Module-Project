import java.util.Scanner

class Common {
    companion object {
        fun <T:Listable>contains(name: String, ar: MutableList<T>): Boolean {
            for (item in ar) {
                if (item.name == name) {
                    return true
                }
            }
            return false
        }

        fun <T:Listable>getPick(menu: Menu, list: MutableList<T>, newScreen:String, editScreen: String, backScreen: String):Int {
            while (true) {
                println("Введите номер нужного пункта меню");
                val input = Scanner(System.`in`).nextLine().toIntOrNull()
                when (input) {
                    0 -> {
                        menu.screen = newScreen
                        return Menu.UNPICKED
                    }
                    list.size+1 -> {
                        menu.screen = backScreen
                        return Menu.UNPICKED
                    }
                    in 1..list.size -> {
                        menu.screen = editScreen
                        return input!!-1
                    }
                    null -> println("Нужно ввести число")
                    else -> println("Нет такого пункта меню. Нужно ввести корректное число")
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
}