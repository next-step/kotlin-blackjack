package blackjack.extensions

fun String.fromYNToBoolean(): Boolean {
    return this == "Y" || this == "y"
}
