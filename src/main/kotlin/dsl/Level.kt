package dsl

data class Level private constructor(val level: Int) {

    companion object {
        private const val NOT_FOUND_LEVEL_MESSAGE = "%s에 해당하는 레벨이 없습니다"
        private const val MINIMUM_LEVEL = 1
        private const val MAXIMUM_LEVEL = 5
        private val CACHE: Map<Int, Level> = (MINIMUM_LEVEL..MAXIMUM_LEVEL).associateWith(::Level)

        fun of(level: Int): Level = CACHE[level]
            ?: throw IllegalArgumentException(NOT_FOUND_LEVEL_MESSAGE.format(level))
    }
}
