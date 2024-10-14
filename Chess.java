import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class Chess extends JFrame
{
private Container container;
private JButton chessButton[][];
private JPanel chessPanel;
private int sourceRow,targetRow;
private int sourceCol,targetCol;
private boolean whiteChance,blackChance;

public Chess()
{
super("TM Chess");
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
chessButton=new JButton[8][8];
chessPanel=new JPanel();
chessPanel.setLayout(new GridLayout(8,8));
setPositions();
}

public boolean getWhitePieceId(String id)
{
if(id.equals("white_pawn") || id.equals("white_king") || id.equals("white_queen") || id.equals("white_rook") || id.equals("white_knight") || id.equals("white_bishop")) return true;
return false;
}

public boolean getBlackPieceId(String id)
{
if(id.equals("black_pawn") || id.equals("black_king") || id.equals("black_queen") || id.equals("black_rook") || id.equals("black_knight") || id.equals("black_bishop")) return true;
return false;
}

//validation move for white_pawn
public boolean moveWhitePawn()
{
//check for down direction
boolean targetFound=false;
int nRow=sourceRow+1;
int nCol=sourceCol;
if(nRow>=0 && nRow<8 && nCol>=0 && nCol<8 && nRow==targetRow && nCol==targetCol)
{
//if co-ordiantes are valid and some piece is present at target then we return;
targetFound=true;
if(chessButton[targetRow][targetCol].getActionCommand().equals("")==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}

//check for 2 diagonal directions
int rDiagonalArr[]={1,1};
int cDiagonalArr[]={-1,1};
for(int i=0;i<2;i++)
{
nRow=sourceRow+rDiagonalArr[i];
nCol=sourceCol+cDiagonalArr[i];
if(nRow>=0 && nRow<8 && nCol>=0 && nCol<8 && nRow==targetRow && nCol==targetCol)
{
//if co-ordiantes are valid and if other than black piece is present at target then we return;
targetFound=true;
String targetPieceId=chessButton[targetRow][targetCol].getActionCommand();
if(getBlackPieceId(targetPieceId)==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}
//if move is not valid ,i.e target is out of range
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation for white_pawn ends here

//validation move for black_pawn
public boolean moveBlackPawn()
{
//check for up direction
boolean targetFound=false;
int nRow=sourceRow-1;
int nCol=sourceCol;
if(nRow>=0 && nRow<8 && nCol>=0 && nCol<8 && nRow==targetRow && nCol==targetCol)
{
//if co-ordiantes are valid and some piece is present at target then we return;
targetFound=true;
if(chessButton[targetRow][targetCol].getActionCommand().equals("")==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}

//check for 2 diagonal directions
int rDiagonalArr[]={-1,-1};
int cDiagonalArr[]={-1,1};
for(int i=0;i<2;i++)
{
nRow=sourceRow+rDiagonalArr[i];
nCol=sourceCol+cDiagonalArr[i];
if(nRow>=0 && nRow<8 && nCol>=0 && nCol<8 && nRow==targetRow && nCol==targetCol)
{
//if co-ordiantes are valid and if other than white piece is present at target then we return;
targetFound=true;
String targetPieceId=chessButton[targetRow][targetCol].getActionCommand();
if(getWhitePieceId(targetPieceId)==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}
//if move is not valid ,i.e target is out of range
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black pawn ends here

//validation of white rook starts here
public boolean moveWhiteRook()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean targetFound=false;
//checking if target is present or not
if(sourceRow!=targetRow && sourceCol==targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) up=true;
else if(diff<0) down=true;
}
if(sourceRow==targetRow && sourceCol!=targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceCol-targetCol;
if(diff>0) left=true;
else if(diff<0) right=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

if(up==true)
{
for(int row=nRow-1;row>=targetRow;row--)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(down==true)
{
for(int row=nRow+1;row<=targetRow;row++)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(left==true)
{
for(int col=nCol-1;col>=targetCol;col--)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(right==true)
{
for(int col=nCol+1;col<=targetCol;col++)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation of white rook ends here

//validation of black rook starts here
public boolean moveBlackRook()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean targetFound=false;
//checking if target is present or not
if(sourceRow!=targetRow && sourceCol==targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) up=true;
else if(diff<0) down=true;
}
if(sourceRow==targetRow && sourceCol!=targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceCol-targetCol;
if(diff>0) left=true;
else if(diff<0) right=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

if(up==true)
{
for(int row=nRow-1;row>=targetRow;row--)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(down==true)
{
for(int row=nRow+1;row<=targetRow;row++)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(left==true)
{
for(int col=nCol-1;col>=targetCol;col--)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(right==true)
{
for(int col=nCol+1;col<=targetCol;col++)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black rook ends here

//validation of white bishop starts here
public boolean moveWhiteBishop()
{
int nRow=-1;
int nCol=-1;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
if((sourceRow-sourceCol)==(targetRow-targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topLeft=true;
else if(diff<0) bottomRight=true;
}
if((sourceRow+sourceCol)==(targetRow+targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topRight=true;
else if(diff<0) bottomLeft=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

int row=-1;
int col=-1;
if(topLeft==true)
{
for(row=nRow-1,col=nCol-1;row>=targetRow && col>=targetCol;row--,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomRight==true)
{
for(row=nRow+1,col=nCol+1;row<=targetRow && col<=targetCol;row++,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(topRight==true)
{
for(row=nRow-1,col=nCol+1;row>=targetRow && col<=targetCol;row--,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomLeft==true)
{
for(row=nRow+1,col=nCol-1;row<=targetRow && col>=targetCol;row++,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation of white bishop ends here

//validation of black bishop starts here
public boolean moveBlackBishop()
{
int nRow=-1;
int nCol=-1;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
if((sourceRow-sourceCol)==(targetRow-targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topLeft=true;
else if(diff<0) bottomRight=true;
}
if((sourceRow+sourceCol)==(targetRow+targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topRight=true;
else if(diff<0) bottomLeft=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

int row=-1;
int col=-1;
if(topLeft==true)
{
for(row=nRow-1,col=nCol-1;row>=targetRow && col>=targetCol;row--,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomRight==true)
{
for(row=nRow+1,col=nCol+1;row<=targetRow && col<=targetCol;row++,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(topRight==true)
{
for(row=nRow-1,col=nCol+1;row>=targetRow && col<=targetCol;row--,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomLeft==true)
{
for(row=nRow+1,col=nCol-1;row<=targetRow && col>=targetCol;row++,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black bishop ends here

//validation of white queen starts here
public boolean moveWhiteQueen()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
if(sourceRow!=targetRow && sourceCol==targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) up=true;
else if(diff<0) down=true;
}
if(sourceRow==targetRow && sourceCol!=targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceCol-targetCol;
if(diff>0) left=true;
else if(diff<0) right=true;
}
if((sourceRow-sourceCol)==(targetRow-targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topLeft=true;
else if(diff<0) bottomRight=true;
}
if((sourceRow+sourceCol)==(targetRow+targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topRight=true;
else if(diff<0) bottomLeft=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

if(up==true)
{
for(int row=nRow-1;row>=targetRow;row--)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(down==true)
{
for(int row=nRow+1;row<=targetRow;row++)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(left==true)
{
for(int col=nCol-1;col>=targetCol;col--)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(right==true)
{
for(int col=nCol+1;col<=targetCol;col++)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

int row=-1;
int col=-1;
if(topLeft==true)
{
for(row=nRow-1,col=nCol-1;row>=targetRow && col>=targetCol;row--,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomRight==true)
{
for(row=nRow+1,col=nCol+1;row<=targetRow && col<=targetCol;row++,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(topRight==true)
{
for(row=nRow-1,col=nCol+1;row>=targetRow && col<=targetCol;row--,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomLeft==true)
{
for(row=nRow+1,col=nCol-1;row<=targetRow && col>=targetCol;row++,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation of white queen ends here

//validation of black queen starts here
public boolean moveBlackQueen()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
if(sourceRow!=targetRow && sourceCol==targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) up=true;
else if(diff<0) down=true;
}
if(sourceRow==targetRow && sourceCol!=targetCol)
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceCol-targetCol;
if(diff>0) left=true;
else if(diff<0) right=true;
}
if((sourceRow-sourceCol)==(targetRow-targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topLeft=true;
else if(diff<0) bottomRight=true;
}
if((sourceRow+sourceCol)==(targetRow+targetCol))
{
nRow=sourceRow;
nCol=sourceCol;
targetFound=true;
int diff=sourceRow-targetRow;
if(diff>0) topRight=true;
else if(diff<0) bottomLeft=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

if(up==true)
{
for(int row=nRow-1;row>=targetRow;row--)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(down==true)
{
for(int row=nRow+1;row<=targetRow;row++)
{
String currentPieceId=chessButton[row][sourceCol].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(left==true)
{
for(int col=nCol-1;col>=targetCol;col--)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(right==true)
{
for(int col=nCol+1;col<=targetCol;col++)
{
String currentPieceId=chessButton[sourceRow][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(col!=targetCol && getWhitePieceId(currentPieceId)==true)
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

int row=-1;
int col=-1;
if(topLeft==true)
{
for(row=nRow-1,col=nCol-1;row>=targetRow && col>=targetCol;row--,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomRight==true)
{
for(row=nRow+1,col=nCol+1;row<=targetRow && col<=targetCol;row++,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(topRight==true)
{
for(row=nRow-1,col=nCol+1;row>=targetRow && col<=targetCol;row--,col++)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

if(bottomLeft==true)
{
for(row=nRow+1,col=nCol-1;row<=targetRow && col>=targetCol;row++,col--)
{
String currentPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
if(row!=targetRow && getWhitePieceId(currentPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}
}
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black queen ends here

//validation of white knight starts here
public boolean moveWhiteKnight()
{
int nRow=-1;
int nCol=-1;
boolean upLeft=false;
boolean upRight=false;
boolean downLeft=false;
boolean downRight=false;
boolean rightUp=false;
boolean rightDown=false;
boolean leftUp=false;
boolean leftDown=false;
boolean targetFound=false;
//checking if target is present or not
nRow=sourceRow;
nCol=sourceCol;
if(nRow-2==targetRow && nCol-1==targetCol)
{
nRow=nRow-2;
nCol=nCol-1;
upLeft=true;
targetFound=true;
}
else if(nRow-2==targetRow && nCol+1==targetCol)
{
nRow=nRow-2;
nCol=nCol+1;
upRight=true;
targetFound=true;
}
else if(nRow+2==targetRow && nCol-1==targetCol)
{
nRow=nRow+2;
nCol=nCol-1;
downLeft=true;
targetFound=true;
}
else if(nRow+2==targetRow && nCol+1==targetCol)
{
nRow=nRow+2;
nCol=nCol+1;
downRight=true;
targetFound=true;
}
else if(nRow-1==targetRow && nCol-2==targetCol)
{
nRow=nRow-1;
nCol=nCol-2;
leftUp=true;
targetFound=true;
}
else if(nRow+1==targetRow && nCol-2==targetCol)
{
nRow=nRow+1;
nCol=nCol-2;
leftDown=true;
targetFound=true;
}
else if(nRow-1==targetRow && nCol+2==targetCol)
{
nRow=nRow-1;
nCol=nCol+2;
rightUp=true;
targetFound=true;
}
else if(nRow+1==targetRow && nCol+2==targetCol)
{
nRow=nRow+1;
nCol=nCol+2;
rightDown=true;
targetFound=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//target piece should not be white
int row=-1;
int col=-1;
row=targetRow;
col=targetCol;
String targetPieceId=chessButton[row][col].getActionCommand();
if(getWhitePieceId(targetPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation of white knight ends here

//validation of black knight starts here
public boolean moveBlackKnight()
{
int nRow=-1;
int nCol=-1;
boolean upLeft=false;
boolean upRight=false;
boolean downLeft=false;
boolean downRight=false;
boolean rightUp=false;
boolean rightDown=false;
boolean leftUp=false;
boolean leftDown=false;
boolean targetFound=false;
//checking if target is present or not
nRow=sourceRow;
nCol=sourceCol;
if(nRow-2==targetRow && nCol-1==targetCol)
{
nRow=nRow-2;
nCol=nCol-1;
upLeft=true;
targetFound=true;
}
else if(nRow-2==targetRow && nCol+1==targetCol)
{
nRow=nRow-2;
nCol=nCol+1;
upRight=true;
targetFound=true;
}
else if(nRow+2==targetRow && nCol-1==targetCol)
{
nRow=nRow+2;
nCol=nCol-1;
downLeft=true;
targetFound=true;
}
else if(nRow+2==targetRow && nCol+1==targetCol)
{
nRow=nRow+2;
nCol=nCol+1;
downRight=true;
targetFound=true;
}
else if(nRow-1==targetRow && nCol-2==targetCol)
{
nRow=nRow-1;
nCol=nCol-2;
leftUp=true;
targetFound=true;
}
else if(nRow+1==targetRow && nCol-2==targetCol)
{
nRow=nRow+1;
nCol=nCol-2;
leftDown=true;
targetFound=true;
}
else if(nRow-1==targetRow && nCol+2==targetCol)
{
nRow=nRow-1;
nCol=nCol+2;
rightUp=true;
targetFound=true;
}
else if(nRow+1==targetRow && nCol+2==targetCol)
{
nRow=nRow+1;
nCol=nCol+2;
rightDown=true;
targetFound=true;
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//target piece should not be white
int row=-1;
int col=-1;
row=targetRow;
col=targetCol;
String targetPieceId=chessButton[row][col].getActionCommand();
if(getBlackPieceId(targetPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black knight ends here

//validation of white king starts here
public boolean moveWhiteKing()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
int rArr[]={-1,0,1,0,-1,1,-1,1};
int cArr[]={0,-1,0,1,-1,1,1,-1};
for(int i=0;i<8;i++)
{
nRow=sourceRow+rArr[i];
nCol=sourceCol+cArr[i];
if(nRow==targetRow && nCol==targetCol)
{
targetFound=true;
break;
}
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

String targetPieceId=chessButton[targetRow][targetCol].getActionCommand();
if(getWhitePieceId(targetPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("black_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=false;
blackChance=true;
return true;
}
//validation of white king ends here

//validation of black king starts here
public boolean moveBlackKing()
{
int nRow=-1;
int nCol=-1;
boolean up=false;
boolean down=false;
boolean left=false;
boolean right=false;
boolean topLeft=false;
boolean topRight=false;
boolean bottomLeft=false;
boolean bottomRight=false;
boolean targetFound=false;
//checking if target is present or not
int rArr[]={-1,0,1,0,-1,1,-1,1};
int cArr[]={0,-1,0,1,-1,1,1,-1};
for(int i=0;i<8;i++)
{
nRow=sourceRow+rArr[i];
nCol=sourceCol+cArr[i];
if(nRow==targetRow && nCol==targetCol)
{
targetFound=true;
break;
}
}
//if target is not found,it means unvalid move
if(targetFound==false) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

String targetPieceId=chessButton[targetRow][targetCol].getActionCommand();
if(getBlackPieceId(targetPieceId)==true) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return false;
}

//if everything is valid
Icon currentIcon=chessButton[sourceRow][sourceCol].getIcon();
String currentButtonId=chessButton[sourceRow][sourceCol].getActionCommand();
String targetButtonId=chessButton[targetRow][targetCol].getActionCommand();
chessButton[sourceRow][sourceCol].setIcon(null);
chessButton[sourceRow][sourceCol].setActionCommand("");
chessButton[targetRow][targetCol].setIcon(currentIcon);
chessButton[targetRow][targetCol].setActionCommand(currentButtonId);
if(targetButtonId.equals("white_king"))
{
JOptionPane.showMessageDialog(this,"Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
System.exit(0);
}
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
whiteChance=true;
blackChance=false;
return true;
}
//validation of black king ends here

public void movePiece()
{
//if the source and target is same ,then we reset and give chance to same color piece
if(sourceRow==targetRow && sourceCol==targetCol) 
{
sourceRow=-1;
targetRow=-1;
sourceCol=-1;
targetCol=-1;
return;
}
if(whiteChance==true)
{
if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_pawn")==true)
{
moveWhitePawn();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_rook")==true)
{
moveWhiteRook();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_bishop")==true)
{
moveWhiteBishop();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_queen")==true)
{
moveWhiteQueen();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_knight")==true)
{
moveWhiteKnight();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("white_king")==true)
{
moveWhiteKing();
return;
}
}//white chance if ends here
else
{
if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_pawn")==true)
{
moveBlackPawn();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_rook")==true)
{
moveBlackRook();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_bishop")==true)
{
moveBlackBishop();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_queen")==true)
{
moveBlackQueen();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_knight")==true)
{
moveBlackKnight();
return;
}
else if(chessButton[sourceRow][sourceCol].getActionCommand().equals("black_king")==true)
{
moveBlackKing();
return;
}
}//black chance else ends here
}//move piece function ends here

public void makeChessBoard()
{
Color evenColorString=new Color(111,115,210);
Color oddColorString=new Color(157,172,255);
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
//placing for white pieces starts here
if(i==0 && (j==0 || j==7))
{
ImageIcon icon = new ImageIcon("images/white_rook.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_rook");
}
else if(i==0 && (j==1 || j==6))
{
ImageIcon icon = new ImageIcon("images/white_knight.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_knight");
}
else if(i==0 && (j==2 || j==5))
{
ImageIcon icon = new ImageIcon("images/white_bishop.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_bishop");
}
else if(i==0 && j==3)
{
ImageIcon icon = new ImageIcon("images/white_king.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_king");
}
else if(i==0 && j==4)
{
ImageIcon icon = new ImageIcon("images/white_queen.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_queen");
}
else if(i==1)
{
ImageIcon icon = new ImageIcon("images/white_pawn.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("white_pawn");
}
//placing for black pieces starts here
else if(i==6)
{
ImageIcon icon = new ImageIcon("images/black_pawn.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_pawn");
}
else if(i==7 && (j==0 || j==7))
{
ImageIcon icon = new ImageIcon("images/black_rook.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_rook");
}
else if(i==7 && (j==1 || j==6))
{
ImageIcon icon = new ImageIcon("images/black_knight.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_knight");
}
else if(i==7 && (j==2 || j==5))
{
ImageIcon icon = new ImageIcon("images/black_bishop.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_bishop");
}
else if(i==7 && j==3)
{
ImageIcon icon = new ImageIcon("images/black_king.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_king");
}
else if(i==7 && j==4)
{
ImageIcon icon = new ImageIcon("images/black_queen.png");
chessButton[i][j]=new JButton(icon);
chessButton[i][j].setActionCommand("black_queen");
}
//if no piece was there
else
{
chessButton[i][j]=new JButton();
chessButton[i][j].setActionCommand("");
}
if(j%2==0) 
{
chessButton[i][j].setBackground(evenColorString);
LineBorder border=new LineBorder(evenColorString,0);
chessButton[i][j].setBorder(border);
}
else
{
chessButton[i][j].setBackground(oddColorString);
LineBorder border=new LineBorder(oddColorString,0);
chessButton[i][j].setBorder(border);
}
int row=i;
int col=j;
chessButton[i][j].addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
//if first time it click on board,then it is selected as source 
if(sourceRow==-1 && sourceCol==-1)
{
sourceRow=row;
sourceCol=col;
}
else if(targetRow==-1 && targetCol==-1) //second time it is selected as target
{
targetRow=row;
targetCol=col;
}
//if none is -1 ,then source and target is selected
if(sourceRow!=-1 && sourceCol!=-1 && targetRow!=-1 && targetCol!=-1)
{
if((whiteChance==true && getWhitePieceId(chessButton[sourceRow][sourceCol].getActionCommand())==true) || (blackChance==true && getBlackPieceId(chessButton[sourceRow][sourceCol].getActionCommand())==true))
{
movePiece();
}
else //if it clicks on the block ,which does not have any piece
{
sourceRow=-1;
sourceCol=-1;
targetRow=-1;
targetCol=-1;
}
}
}
});
chessPanel.add(chessButton[i][j]);
}
//swapping color for next row
Color tmp=evenColorString;
evenColorString=oddColorString;
oddColorString=tmp;
}
}

void setPositions()
{
container=getContentPane();
makeChessBoard();
container.add(chessPanel);
setSize(550,550);
setLocation(500,150);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public static void main(String gg[])
{
Chess chess=new Chess();
}
}

/*
for every piece ,we follow this simple step
1-> if the source coordinates are valid(we have to change src coodinates acc to piece)and it is equal to target coordinates.
2-> then, checks if there any piece present b/w source coordinated and target coordinates.(not valid for knight)
3-> now,if everything is valid ,then we move the piece
*/