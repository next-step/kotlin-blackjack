package dsl

import dsl.performer.Person
import dsl.performer.PersonBuilder

class IntroduceDsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

fun main() {
    val introduce = introduce {
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
    println("저를 소개합니다 $introduce")
}
