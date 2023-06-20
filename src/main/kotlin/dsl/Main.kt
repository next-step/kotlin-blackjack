package dsl

fun main() {

    val person = introduce {
        name("김진억")
        company("카카오")
        skills {
            soft("커뮤니케이션")
            soft("리더십")
            hard("코틀린")
        }
        languages {
            "한국어" level 999
            "영어" level 1
        }
    }

    println(person)
}
