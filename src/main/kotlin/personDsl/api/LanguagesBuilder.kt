package personDsl.api

interface LanguagesBuilder {
    infix fun String.to(level: Int)
}
