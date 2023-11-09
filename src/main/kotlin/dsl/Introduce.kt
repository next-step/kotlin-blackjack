package dsl

import dsl.builder.PersonBuilder
import dsl.model.Person

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

fun main() {
    val person = introduce {
        name("김파도")
        company("바람")
        skills {
            soft("오호츠크해속 돌고래의 시원함")
            hard("해저화산의 뜨거움")
        }
        languages {
            "Korean" level -1
            "English" level -1
            "Kotlin" level 1
        }
    }
    println("저를 소개합니다 ${person.introduce()}")
}

fun Person.introduce(): String {
    return ""
}
//        return """
//        name=${this.name}\n
//        company=${this.company}
//        skills=[${this.skills.introduce()}]
//        languages=[${this.languages}]
//        """.trimMargin()
//    }

// fun Skills.introduce():String{
//
// }
