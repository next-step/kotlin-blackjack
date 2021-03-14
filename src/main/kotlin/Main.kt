fun main() {
    introduce {
        name("박재성")
        company("우아한형제들")
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

fun languages(initializer: Languages.() -> Unit): Languages {
    return Languages().apply(initializer)
}
