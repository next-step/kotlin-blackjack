package study.domain

fun introduce(init: Resume.() -> Unit): Resume {
    return Resume().apply(init)
}
