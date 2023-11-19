package study

import blackjack.view.Console.present
import io.kotest.core.spec.style.StringSpec

class DefensiveCopyLearn : StringSpec({

    "방어적 복사를 하지 않았을 때의 문제가 되는 코드 예제" {
        val list = mutableListOf<String>()
        list.add("goodNight")
        list.add("goodMorning")
        list.add("niceYoMeetYou")
        val nonDefensiveClass = NonDefensiveClass(list)
        println("BEFORE : ${nonDefensiveClass.present()}")
        list.clear()
        println("AFTER : ${nonDefensiveClass.present()}")
    }

    "방어적 복사를 적용한 코드 예제" {
        val list = mutableListOf<String>()
        list.add("goodNight")
        list.add("goodMorning")
        list.add("niceYoMeetYou")
        val defensiveClass = DefensiveClass(list.toList())
        println("BEFORE : ${defensiveClass.present()}")
        list.clear()
        println("AFTER : ${defensiveClass.present()}")
    }
})

class NonDefensiveClass(
    val strings: MutableList<String>
) {
    fun present(): String {
        return strings.toString()
    }
}

class DefensiveClass {
    private val _strings: MutableList<String> = strings.toMutableList()
    val strings: List<String> get() = _strings.toList()

    fun present(): String {
        return strings.toString()
    }
}
