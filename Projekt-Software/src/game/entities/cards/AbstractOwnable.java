package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.Player;

public abstract class AbstractOwnable extends AbstractCard{

	protected Player owner; 
	protected int cardNo;
	
	
	public AbstractOwnable(Outputable output, CardType cardType, int cardNo) {
		super(output, cardType);
		owner = null;
		
	}
	
	public Player getOwner(){
		return owner; 
	}
	public void setOwner(Player player){
		owner = player; 
	}
	
	/****************************************************************************
	 * Function can only be called by an inheriting class						*
	 * If Card has no owner. Player can save the card                           *
	 ***************************************************************************/

	@Override
	public String toString(){
		String str = super.toString();
		if(owner != null)
			str += ", owner=" + owner.getName();
		
		return str;
	}
}
