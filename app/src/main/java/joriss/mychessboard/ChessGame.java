package joriss.mychessboard;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class ChessGame extends AppCompatActivity {

    private int checkerboard_size = 10;
    private Pieces [][] checkerBoard; //[rows][collumns]
    private Pieces [] usedFields; //an array with the pieces on all black fields
    private String[] fieldIndices;
    private ArrayList<ImageView> fieldViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers_game);
        this.fieldIndices = new String[50];
        this.usedFields = new Pieces[50];

        fieldViews = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            int viewId = getResources().getIdentifier("f"+i, "id", getPackageName());
            ImageView fieldView = (ImageView) findViewById(viewId);
            if (fieldView != null) {
              //  todo na fix comment weghalen
                // fieldView.setOnClickListener(onFieldClickListener);
                fieldViews.add(fieldView);
            }
        }

        fillIndices();
        newGame();

        updateBoard();
    }

    /**
     * fills the indices of the imageviews
     */
    private void fillIndices(){
        for(int i=0; i<50; i++){
            String number = Integer.toString(i+1);
            this.fieldIndices[i] = "f"+number;
        }
    }

    //TODO maak methode die voor elke veld-view de background aanpast naar een plaatje dat hoort bij die enum
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private void updateusedFields(){
        int blackField = 0;
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                if ((i+j)%2 == 1){
                    usedFields[blackField] = checkerBoard[i][j];//TODO copy constructor? if needed?
                    blackField++;
                }
            }

        }
//        if (blackField >= 50)
            //TODO Errormessage?
    }



    private void updateBoardImages(){
        for(int i=0; i<50; i++){
            ImageView v;
            Drawable d;
            d = usedFields[i ].pieceDrawable();
            v = fieldViews.get(i);
            if (v.getDrawable() != d){
                v.setBackground(d);
//                v.setImageResource(d);

            }
        }

        /*
        for (ImageView v : fieldViews){
            //je hebt een ImageView
            //je wilt weten welke bijbehorende waarde Pieces heeft in usedFields[]
            //en vervolgens deze view aanpassen naar de juiste background
            v.getResources().
            v.setBackground(drawableOf);
        }
        */
    }

    private void updateBoard(){
        updateusedFields();
        updateBoardImages();

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum Pieces {
        empty,
        whiteStone,
        whiteDam,
        blackStone,
        blackDam;
        public Drawable pieceDrawable() {
            /*todo fix this!! this should return Drawable's
            switch (this){
                default:
                    return @drawable/R.drawable.image1
                case
                        empty:
                    Drawable d = @drawable/R.drawable.black_field;
                    return @drawable/R.drawable.black_field;
                case
                        blackDam: return @drawable/R.drawable.pink_dam;
                case blackStone: return @drawable/R.drawable.pink_stone;
                case whiteDam: return @drawable/R.drawable.white_dam;
                case whiteStone: return @DrawableRes/R.drawable.white_stone;
            }
            */
            return null;
        }
    }

    /**
     * starts a new game, putting all pieces on default positions
     */
    private void newGame() {
        this.checkerBoard = new Pieces[checkerboard_size][checkerboard_size];
        Arrays.fill(checkerBoard, Pieces.empty);

        placeWhite();
        placeBlack();
    }

    /**
     * places White stones in default positions
     */
    private void placeWhite() {
        for (int i=0; i<4; i++){
            for (int j=0; j<10; j++){
                if(i%2 == 1){
                    if (j%2 == 0) {
                        this.checkerBoard[i][j] = Pieces.whiteStone;
                    }
                }
                else{
                    if(j%2 == 1){
                        this.checkerBoard[i][j] = Pieces.whiteStone;
                    }
                }
            }
        }
    }

    /**
     * places Black stones in default positions
     */
    private void placeBlack() {
        for (int i=6; i<10; i++){
            for (int j=0; j<10; j++){
                if(i%2 == 1){
                    if (j%2 == 0) {
                        this.checkerBoard[i][j] = Pieces.blackStone;
                    }
                }
                else{
                    if(j%2 == 1){
                        this.checkerBoard[i][j] = Pieces.blackStone;
                    }
                }
            }
        }
    }

    /**
     * OnClickListener for all fields
     */

    /* todo sebas z'n code fixen? ik heb er niks aan gedaan en hij ging huilen :(
    private View.OnClickListener onFieldClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId( == R.id.f1)) {

            }
        }
    };

*/

}
