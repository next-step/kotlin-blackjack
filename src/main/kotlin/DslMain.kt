fun main() {
    introduce {
        name("서진혁")
        company("nextstep")
        skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 3
        }
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}
