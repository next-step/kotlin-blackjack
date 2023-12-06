package introduce.domain

import introduce.IntroduceMarker

@IntroduceMarker
class Skills {
    private val _soft: MutableList<String> = mutableListOf()
    private val _hard: MutableList<String> = mutableListOf()

    val soft: List<String> get() = _soft.toList()
    val hard: List<String> get() = _hard.toList()

    fun soft(value: String) {
        _soft.add(value)
    }

    fun hard(value: String) {
        _hard.add(value)
    }
}
