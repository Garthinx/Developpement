package com.example.projetfinal;

import java.util.Arrays;

public class GameLogic {
    private int playerTurn;
    private int numPlayer;
    private int gameTurn;
    private int myTurn;
    private int diceRes;
    private PixLocation px = new PixLocation();

    protected int[] boardGreen = new int[58];
    protected int[] boardYellow = new int[58];
    protected int[] boardRed = new int[58];
    protected int[] boardBlue = new int[58];
    protected int xc;
    protected int yc;

    /**
     * @return
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * @param playerTurn
     */
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * @return
     */
    public int getNumPlayer() {
        return numPlayer;
    }

    /**
     * @param numPlayer
     */
    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    /**
     * @return
     */
    public int getGameTurn() {
        return gameTurn;
    }

    /**
     * @param gameTurn
     */
    public void setGameTurn(int gameTurn) {
        this.gameTurn = gameTurn;
    }

    /**
     * @return
     */
    public int getMyTurn() {
        return myTurn;
    }

    /**
     *
     */
    public void setMyTurn() {
        this.myTurn = this.gameTurn % this.numPlayer;
    }

    /**
     * @return
     */
    public int getDiceRes() {
        return diceRes;
    }

    /**
     * @param diceRes
     */
    public void setDiceRes(int diceRes) {
        this.diceRes = diceRes;
    }

    public void initBoardGreen() {
        Arrays.fill(boardGreen, 0);
        boardGreen[0] = 4;
        px.setGreenPix();
    }

    public void initBoardYellow() {
        Arrays.fill(boardYellow, 0);
        boardYellow[0] = 4;
        px.setYellowPix();
    }

    public void initBoardRed() {
        Arrays.fill(boardRed, 0);
        boardRed[0] = 4;
        px.setRedPix();
    }

    public void initBoardBlue() {
        Arrays.fill(boardBlue, 0);
        boardBlue[0] = 4;
        px.setBluePix();
    }

    public void moveGreen() {
        if (this.diceRes == 6) {
            if (xc > 720 && xc < 770 && yc > 80 && yc < 120) {
                boardGreen[1]++;
                paint('g', 663, 80);
                boardGreen[0]--;
                paint(750, 100);
            } else if (xc > 820 && xc < 870 && yc > 80 && yc < 120) {
                boardGreen[1]++;
                paint('g', 663, 80);
                boardGreen[0]--;
                paint(850, 100);
            } else if (xc > 720 && xc < 770 && yc > 180 && yc < 220) {
                boardGreen[1]++;
                paint('g', 663, 80);
                boardGreen[0]--;
                paint(750, 200);

            } else if (xc > 820 && xc < 870 && yc > 180 && yc < 220) {
                boardGreen[1]++;
                paint('g', 663, 80);
                boardGreen[0]--;
                paint(850, 200);
            }
        } else {
            int jump = 0;
            for (int i = 1; i < 58; i++) {
                if (boardGreen[i] != 0) {
                    int xGreen = px.getGreenPix(i) / 1000;
                    int yGreen = px.getGreenPix(i) % 1000;

                    if (xc > xGreen - 40 && xc < xGreen + 40 && yc > yGreen - 40 && yc < yGreen + 40) {
                        if (i + diceRes < 52) {
                            jump = i + diceRes;
                            collision('g', jump);
                            paint(xGreen, yGreen);
                            int x = px.getGreenPix(i + diceRes) / 1000;
                            int y = px.getGreenPix(i + diceRes) % 1000;
                            paint('g', x, y);
                            boardGreen[i + diceRes]++;
                            boardGreen[i]--;
                            break;
                        } else if (i + diceRes >= 52 && i + diceRes < 57) {
                            paint(xGreen, yGreen);
                            int x = px.getGreenPix(i + diceRes) / 1000;
                            int y = px.getGreenPix(i + diceRes) % 1000;
                            paint('g', x, y);
                            boardGreen[i + diceRes]++;
                            jump = i + diceRes;
                            boardGreen[i]--;
                            break;
                        } else if (i + diceRes == 57) {
                            paint(xGreen, yGreen);
                            int x = px.getGreenPix(57) / 1000;
                            int y = px.getGreenPix(57) % 1000;
                            paint('g', x, y);
                            boardGreen[57]++;
                            boardGreen[i]--;
                            paint(x, y);
                            //check win
                            break;
                        } else {
                            int j = i + diceRes;
                            int ret = j - 57;
                            int fp = 57 - ret;
                            paint(xGreen, yGreen);
                            int x = px.getGreenPix(fp) / 1000;
                            int y = px.getGreenPix(fp) % 1000;
                            paint('g', x, y);
                            boardGreen[fp]++;
                            boardGreen[i]--;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < 12; i++) {
                int j = 4 * i + 3;
                if (jump == j) {
                    if (i == 4) {
                        j = 4 * 7 + 3;
                    } else {
                        j += 4;
                    }
                    collision('g', j);
                    int x = px.getGreenPix(jump) / 1000;
                    int y = px.getGreenPix(jump) % 1000;
                    paint(x, y);
                    boardGreen[jump]--;
                    int nx = px.getGreenPix(j) / 1000;
                    int ny = px.getGreenPix(j) % 1000;
                    paint('g', nx, ny);
                    boardGreen[j]++;
                }
            }
        }
    }

    public void moveYellow() {
        if (this.diceRes == 6) {
            if (xc > 70 && xc < 120 && yc > 80 && yc < 120) {
                boardYellow[1]++;
                paint('y', 79, 287);
                boardYellow[0]--;
                paint(100, 100);
            } else if (xc > 170 && xc < 220 && yc > 80 && yc < 120) {
                boardYellow[1]++;
                paint('y', 79, 287);
                boardYellow[0]--;
                paint(200, 100);
            } else if (xc > 70 && xc < 120 && yc > 180 && yc < 220) {
                boardYellow[1]++;
                paint('y', 79, 287);
                boardYellow[0]--;
                paint(100, 200);
            } else if (xc > 170 && xc < 220 && yc > 180 && yc < 220) {
                boardYellow[1]++;
                paint('y', 79, 287);
                boardYellow[0]--;
                paint(200, 200);
            }
        } else {
            int jump = 0;
            for (int i = 1; i < 58; i++) {
                if (boardYellow[i] != 0) {
                    int xYellow = px.getYellowPix(i) / 1000;
                    int yYellow = px.getYellowPix(i) % 1000;

                    if (xc > xYellow - 40 && xc < xYellow + 40 && yc > yYellow - 40 && yc < yYellow + 40) {
                        if (i + diceRes < 52) {
                            jump = i + diceRes;
                            collision('y', jump);
                            paint(xYellow, yYellow);
                            int x = px.getYellowPix(i + diceRes) / 1000;
                            int y = px.getYellowPix(i + diceRes) % 1000;
                            paint('y', x, y);
                            boardYellow[i + diceRes]++;
                            boardYellow[i]--;
                            break;
                        } else if (i + diceRes >= 52 && i + diceRes < 57) {
                            paint(xYellow, yYellow);
                            int x = px.getYellowPix(i + diceRes) / 1000;
                            int y = px.getYellowPix(i + diceRes) % 1000;
                            paint('y', x, y);
                            boardYellow[i + diceRes]++;
                            jump = i + diceRes;
                            boardYellow[i]--;
                            break;
                        } else if (i + diceRes == 57) {
                            paint(xYellow, yYellow);
                            int x = px.getYellowPix(57) / 1000;
                            int y = px.getYellowPix(57) % 1000;
                            paint('y', x, y);
                            boardYellow[57]++;
                            boardYellow[i]--;
                            paint(x, y);
                            //check win
                            break;
                        } else {
                            int j = i + diceRes;
                            int ret = j - 57;
                            int fp = 57 - ret;
                            paint(xYellow, yYellow);
                            int x = px.getYellowPix(fp) / 1000;
                            int y = px.getYellowPix(fp) % 1000;
                            paint('y', x, y);
                            boardYellow[fp]++;
                            boardYellow[i]--;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < 12; i++) {
                int j = 4 * i + 3;
                if (jump == j) {
                    if (i == 4) {
                        j = 4 * 7 + 3;
                    } else {
                        j += 4;
                    }
                    collision('y', j);
                    int x = px.getYellowPix(jump) / 1000;
                    int y = px.getYellowPix(jump) % 1000;
                    paint(x, y);
                    boardYellow[jump]--;
                    int nx = px.getYellowPix(j) / 1000;
                    int ny = px.getYellowPix(j) % 1000;
                    paint('y', nx, ny);
                    boardYellow[j]++;
                }
            }
        }
    }

    public void moveRed() {
        if (this.diceRes == 6) {
            if (xc > 720 && xc < 770 && yc > 730 && yc < 770) {
                boardRed[1]++;
                paint('r', 869, 666);
                boardRed[0]--;
                paint(750, 750);
            } else if (xc > 820 && xc < 870 && yc > 730 && yc < 770) {
                boardRed[1]++;
                paint('r', 869, 666);
                boardRed[0]--;
                paint(850, 750);
            } else if (xc > 720 && xc < 770 && yc > 830 && yc < 870) {
                boardRed[1]++;
                paint('r', 869, 666);
                boardRed[0]--;
                paint(750, 850);
            } else if (xc > 820 && xc < 870 && yc > 830 && yc < 870) {
                boardRed[1]++;
                paint('r', 869, 666);
                boardRed[0]--;
                paint(850, 850);
            }
        } else {
            int jump = 0;
            for (int i = 1; i < 58; i++) {
                if (boardRed[i] != 0) {
                    int xRed = px.getRedPix(i) / 1000;
                    int yRed = px.getRedPix(i) % 1000;

                    if (xc > xRed - 40 && xc < xRed + 40 && yc > yRed - 40 && yc < yRed + 40) {
                        if (i + diceRes < 52) {
                            jump = i + diceRes;
                            collision('r', jump);
                            paint(xRed, yRed);
                            int x = px.getRedPix(i + diceRes) / 1000;
                            int y = px.getRedPix(i + diceRes) % 1000;
                            paint('r', x, y);
                            boardRed[i + diceRes]++;
                            boardRed[i]--;
                            break;
                        } else if (i + diceRes >= 52 && i + diceRes < 57) {
                            paint(xRed, yRed);
                            int x = px.getRedPix(i + diceRes) / 1000;
                            int y = px.getRedPix(i + diceRes) % 1000;
                            paint('r', x, y);
                            boardRed[i + diceRes]++;
                            jump = i + diceRes;
                            boardRed[i]--;
                            break;
                        } else if (i + diceRes == 57) {
                            paint(xRed, yRed);
                            int x = px.getRedPix(57) / 1000;
                            int y = px.getRedPix(57) % 1000;
                            paint('r', x, y);
                            boardRed[57]++;
                            boardRed[i]--;
                            paint(x, y);
                            //check win
                            break;
                        } else {
                            int j = i + diceRes;
                            int ret = j - 57;
                            int fp = 57 - ret;
                            paint(xRed, yRed);
                            int x = px.getRedPix(fp) / 1000;
                            int y = px.getRedPix(fp) % 1000;
                            paint('r', x, y);
                            boardRed[fp]++;
                            boardRed[i]--;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < 12; i++) {
                int j = 4 * i + 3;
                if (jump == j) {
                    if (i == 4) {
                        j = 4 * 7 + 3;
                    } else {
                        j += 4;
                    }
                    collision('r', j);
                    int x = px.getRedPix(jump) / 1000;
                    int y = px.getRedPix(jump) % 1000;
                    paint(x, y);
                    boardRed[jump]--;
                    int nx = px.getRedPix(j) / 1000;
                    int ny = px.getRedPix(j) % 1000;
                    paint('r', nx, ny);
                    boardRed[j]++;
                }
            }
        }
    }

    public void moveBlue() {
        if (this.diceRes == 6) {
            if (xc > 70 && xc < 120 && yc > 730 && yc < 770) {
                boardBlue[1]++;
                paint('b', 285, 868);
                boardBlue[0]--;
                paint(100, 750);
            } else if (xc > 170 && xc < 220 && yc > 730 && yc < 770) {
                boardBlue[1]++;
                paint('b', 285, 868);
                boardBlue[0]--;
                paint(200, 750);
            } else if (xc > 70 && xc < 120 && yc > 830 && yc < 870) {
                boardBlue[1]++;
                paint('b', 285, 868);
                boardBlue[0]--;
                paint(100, 850);
            } else if (xc > 170 && xc < 220 && yc > 830 && yc < 870) {
                boardBlue[1]++;
                paint('b', 285, 868);
                boardBlue[0]--;
                paint(200, 850);
            }
        } else {
            int jump = 0;
            for (int i = 1; i < 58; i++) {
                if (boardBlue[i] != 0) {
                    int xBlue = px.getBluePix(i) / 1000;
                    int yBlue = px.getBluePix(i) % 1000;

                    if (xc > xBlue - 40 && xc < xBlue + 40 && yc > yBlue - 40 && yc < yBlue + 40) {
                        if (i + diceRes < 52) {
                            jump = i + diceRes;
                            collision('r', jump);
                            paint(xBlue, yBlue);
                            int x = px.getBluePix(i + diceRes) / 1000;
                            int y = px.getBluePix(i + diceRes) % 1000;
                            paint('b', x, y);
                            boardBlue[i + diceRes]++;
                            boardBlue[i]--;
                            break;
                        } else if (i + diceRes >= 52 && i + diceRes < 57) {
                            paint(xBlue, yBlue);
                            int x = px.getBluePix(i + diceRes) / 1000;
                            int y = px.getBluePix(i + diceRes) % 1000;
                            paint('b', x, y);
                            boardBlue[i + diceRes]++;
                            jump = i + diceRes;
                            boardBlue[i]--;
                            break;
                        } else if (i + diceRes == 57) {
                            paint(xBlue, yBlue);
                            int x = px.getBluePix(57) / 1000;
                            int y = px.getBluePix(57) % 1000;
                            paint('b', x, y);
                            boardBlue[57]++;
                            boardBlue[i]--;
                            paint(x, y);
                            //check win
                            break;
                        } else {
                            int j = i + diceRes;
                            int ret = j - 57;
                            int fp = 57 - ret;
                            paint(xBlue, yBlue);
                            int x = px.getBluePix(fp) / 1000;
                            int y = px.getBluePix(fp) % 1000;
                            paint('b', x, y);
                            boardBlue[fp]++;
                            boardBlue[i]--;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < 12; i++) {
                int j = 4 * i + 3;
                if (jump == j) {
                    if (i == 4) {
                        j = 4 * 7 + 3;
                    } else {
                        j += 4;
                    }
                    collision('r', j);
                    int x = px.getBluePix(jump) / 1000;
                    int y = px.getBluePix(jump) % 1000;
                    paint(x, y);
                    boardBlue[jump]--;
                    int nx = px.getBluePix(j) / 1000;
                    int ny = px.getBluePix(j) % 1000;
                    paint('b', nx, ny);
                    boardBlue[j]++;
                }
            }
        }
    }

    /**
     *
     * @param color
     * @param loc
     */
    public void collision(char color, int loc){

    }

    /**
     * paint sans couleur, overwrite l'ancienne case
     * @param x
     * @param y
     */
    public void paint(int x, int y){

    }

    /**
     * paint avec coleur, g=vert, y=jaune, r=rouge, b=bleu
     * @param color
     * @param x
     * @param y
     */
    public void paint(char color, int x, int y){

    }
}