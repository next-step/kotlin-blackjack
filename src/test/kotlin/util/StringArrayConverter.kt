package util

import org.junit.jupiter.params.converter.SimpleArgumentConverter

class StringArrayConverter : SimpleArgumentConverter() {
    override fun convert(source: Any, targetType: Class<*>): Any {
        return if (source is String && Array<String>::class.java.isAssignableFrom(targetType)) {
            source.split("\\s*;\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else {
            throw IllegalArgumentException(
                "Conversion from " + source.javaClass + " to " +
                    targetType + " not supported."
            )
        }
    }
}
