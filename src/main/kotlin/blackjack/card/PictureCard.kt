package blackjack.card

class PictureCard(
    val picture: CardPicture,
    val pattern: CardPattern
) : BlackJackCard {

    override fun toString(): String {
        return "${picture.pictureName}${pattern.patternName}"
    }
}
