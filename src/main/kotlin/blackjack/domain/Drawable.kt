package blackjack.domain

interface Drawable {
    fun canDraw(player: Player): Boolean
}
