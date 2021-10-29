package com.example.newu.branokod3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.newu.branokod3.MyItemsSoko.NUM;
import static com.example.newu.branokod3.MyItemsVarsity.NUMV;
import static com.example.newu.branokod3.MyItemsVarsity.PRICE;
import static com.example.newu.branokod3.MyItemsVarsity.TIME;
import static com.example.newu.branokod3.sokopayment.sokopricetotal;
import static java.lang.String.valueOf;

public class varsitypayment extends AppCompatActivity {
    private static final String TAG ="varsitypayment" ;
    public static final String varsityamounttotal ="YAVARSITY" ;

    TextView t1;
    ImageButton airtel,saf;
    int totalup,total;
    int yote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsitypayment);
        t1= findViewById(R.id.servicefeeamount);
        airtel= findViewById(R.id.imageButton3);
        saf= findViewById(R.id.imageButton4);

        Intent inten=getIntent();
        String sokoprice=inten.getStringExtra(sokopricetotal);


        airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(varsitypayment.this,airtelpaymentmode.class);
                intent.putExtra("YAVARSITY",totalup);
                startActivity(intent);
            }
        });

        saf.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent inte = new Intent(varsitypayment.this, safaricompaymentmode.class);
                                       inte.putExtra("YAVARSITY", totalup);
                                       startActivity(inte);

                                   }
        });


        calculatepricevarsity();
    }

    int calculatepricevarsity() {
        Intent in = getIntent();
        ArrayList<String> prices = in.getStringArrayListExtra(PRICE);
        ArrayList<String> times = in.getStringArrayListExtra(TIME);
        int length = in.getIntExtra(NUMV, 0);
        Log.d(TAG, "calculatepricevarsity: " + prices);
        Log.d(TAG, "calculatepricevarsity: " + times);


        int count03 = 0, count04 = 0, count08 = 0, count13 = 0, count14 = 0, count18 = 0, count23 = 0, count24 = 0,
                count28 = 0, count33 = 0, count34 = 0, count38 = 0, count43 = 0, count44 = 0, count48 = 0, count53 = 0,
                count54 = 0, count58 = 0, count63 = 0, count64 = 0, count68 = 0, count73 = 0, count74 = 0, count78 = 0,
                count83 = 0, count84 = 0, count88 = 0, count93 = 0, count94 = 0, count98 = 0, count103 = 0, count104 = 0,
                count108 = 0;
        totalup = 0;
        int wantedprice;
        if(length>0){
        for (int i = 0; i < length; i++) {
            wantedprice = Integer.parseInt(prices.get(i));
            int wantedtime = Integer.parseInt(times.get(i));


            if (wantedprice <= 100) {
                if (wantedtime == 3) {
                    count03 = count03 + 1;
                }
                if (wantedtime == 4) {
                    count04 = count04 + 1;
                }
                if (wantedtime == 8) {
                    count08 = count08 + 1;
                }
            }

            if (wantedprice > 100 && wantedprice <= 200) {
                if (wantedtime == 3) {
                    count13 = count13 + 1;
                }
                if (wantedtime == 4) {
                    count14 = count14 + 1;
                }
                if (wantedtime == 8) {
                    count18 = count18 + 1;
                }
            }

            if (wantedprice > 200 && wantedprice <= 300) {

                if (wantedtime == 3) {
                    count23 = count23 + 1;
                }
                if (wantedtime == 4) {
                    count24 = count24 + 1;
                }
                if (wantedtime == 8) {
                    count28 = count28 + 1;
                }
            }

            if (wantedprice > 300 && wantedprice <= 400) {
                if (wantedtime == 3) {
                    count33 = count33 + 1;
                }
                if (wantedtime == 4) {
                    count34 = count34 + 1;
                }
                if (wantedtime == 8) {
                    count38 = count38 + 1;
                }
            }

            if (wantedprice > 400 && wantedprice <= 500) {
                if (wantedtime == 3) {
                    count43 = count43 + 1;
                }
                if (wantedtime == 4) {
                    count44 = count44 + 1;
                }
                if (wantedtime == 8) {
                    count48 = count48 + 1;
                }
            }

            if (wantedprice > 500 && wantedprice <= 600) {
                if (wantedtime == 3) {
                    count53 = count53 + 1;
                }
                if (wantedtime == 4) {
                    count54 = count54 + 1;
                }
                if (wantedtime == 8) {
                    count58 = count58 + 1;
                }
            }
            if (wantedprice > 600 && wantedprice <= 700) {
                if (wantedtime == 3) {
                    count63 = count63 + 1;
                }
                if (wantedtime == 4) {
                    count64 = count64 + 1;
                }
                if (wantedtime == 8) {
                    count68 = count68 + 1;
                }
            }

            if (wantedprice > 700 && wantedprice <= 800) {
                if (wantedtime == 3) {
                    count73 = count73 + 1;
                }
                if (wantedtime == 4) {
                    count74 = count74 + 1;
                }
                if (wantedtime == 8) {
                    count78 = count78 + 1;
                }
            }

            if (wantedprice > 800 && wantedprice <= 900) {
                if (wantedtime == 3) {
                    count83 = count83 + 1;
                }
                if (wantedtime == 4) {
                    count84 = count84 + 1;
                }
                if (wantedtime == 8) {
                    count88 = count88 + 1;
                }
            }

            if (wantedprice > 900 && wantedprice <= 1000) {
                if (wantedtime == 3) {
                    count93 = count93 + 1;
                }
                if (wantedtime == 4) {
                    count94 = count94 + 1;
                }
                if (wantedtime == 8) {
                    count98 = count98 + 1;
                }
            }

            if (wantedprice > 1000) {
                if (wantedtime == 3) {
                    count103 = count103 + 1;
                }
                if (wantedtime == 4) {
                    count104 = count104 + 1;
                }
                if (wantedtime == 8) {
                    count108 = count108 + 1;
                }
            }
        }


        if (count03 == 1) {
            totalup = totalup + 19;
        }
        if (count03 == 2) {
            totalup = totalup + 29;
        }
        if (count03 == 3) {
            totalup = totalup + 39;
        }
        if (count03 == 4) {
            totalup = totalup + 49;
        }
        if (count04 == 1) {
            totalup = totalup + 29;
        }
        if (count04 == 2) {
            totalup = totalup + 49;
        }
        if (count04 == 3) {
            totalup = totalup + 69;
        }
        if (count04 == 4) {
            totalup = totalup + 89;
        }
        if (count08 == 1) {
            totalup = totalup + 49;
        }
        if (count08 == 2) {
            totalup = totalup + 79;
        }
        if (count08 == 3) {
            totalup = totalup + 109;
        }
        if (count08 == 4) {
            totalup = totalup + 139;
        }

        if (count13 == 1) {
            totalup = totalup + 29;
        }
        if (count13 == 2) {
            totalup = totalup + 39;
        }
        if (count13 == 3) {
            totalup = totalup + 49;
        }
        if (count13 == 4) {
            totalup = totalup + 59;
        }
        if (count14 == 1) {
            totalup = totalup + 39;
        }
        if (count14 == 2) {
            totalup = totalup + 59;
        }
        if (count14 == 3) {
            totalup = totalup + 79;
        }
        if (count14 == 4) {
            totalup = totalup + 99;
        }
        if (count18 == 1) {
            totalup = totalup + 59;
        }
        if (count18 == 2) {
            totalup = totalup + 89;
        }
        if (count18 == 3) {
            totalup = totalup + 119;
        }
        if (count18 == 4) {
            totalup = totalup + 149;
        }
        if (count23 == 1) {
            totalup = totalup + 39;
        }
        if (count23 == 2) {
            totalup = totalup + 49;
        }
        if (count23 == 3) {
            totalup = totalup + 59;
        }
        if (count23 == 4) {
            totalup = totalup + 69;
        }
        if (count24 == 1) {
            totalup = totalup + 49;
        }
        if (count24 == 2) {
            totalup = totalup + 69;
        }
        if (count24 == 3) {
            totalup = totalup + 89;
        }
        if (count24 == 4) {
            totalup = totalup + 109;
        }
        if (count28 == 1) {
            totalup = totalup + 69;
        }
        if (count28 == 2) {
            totalup = totalup + 99;
        }
        if (count28 == 3) {
            totalup = totalup + 129;
        }
        if (count28 == 4) {
            totalup = totalup + 159;
        }
        if (count33 == 1) {
            totalup = totalup + 49;
        }
        if (count33 == 2) {
            totalup = totalup + 59;
        }
        if (count33 == 3) {
            totalup = totalup + 69;
        }
        if (count33 == 4) {
            totalup = totalup + 79;
        }
        if (count34 == 1) {
            totalup = totalup + 59;
        }
        if (count34 == 2) {
            totalup = totalup + 79;
        }
        if (count34 == 3) {
            totalup = totalup + 99;
        }
        if (count34 == 4) {
            totalup = totalup + 119;
        }
        if (count38 == 1) {
            totalup = totalup + 79;
        }
        if (count38 == 2) {
            totalup = totalup + 109;
        }
        if (count38 == 3) {
            totalup = totalup + 139;
        }
        if (count38 == 4) {
            totalup = totalup + 169;
        }

        if (count43 == 1) {
            totalup = totalup + 59;
        }
        if (count43 == 2) {
            totalup = totalup + 69;
        }
        if (count43 == 3) {
            totalup = totalup + 79;
        }
        if (count43 == 4) {
            totalup = totalup + 89;
        }
        if (count44 == 1) {
            totalup = totalup + 69;
        }
        if (count44 == 2) {
            totalup = totalup + 89;
        }
        if (count44 == 3) {
            totalup = totalup + 109;
        }
        if (count44 == 4) {
            totalup = totalup + 129;
        }
        if (count48 == 1) {
            totalup = totalup + 89;
        }
        if (count48 == 2) {
            totalup = totalup + 119;
        }
        if (count48 == 3) {
            totalup = totalup + 149;
        }
        if (count48 == 4) {
            totalup = totalup + 179;
        }

        if (count53 == 1) {
            totalup = totalup + 69;
        }
        if (count53 == 2) {
            totalup = totalup + 79;
        }
        if (count53 == 3) {
            totalup = totalup + 89;
        }
        if (count53 == 4) {
            totalup = totalup + 99;
        }
        if (count54 == 1) {
            totalup = totalup + 79;
        }
        if (count54 == 2) {
            totalup = totalup + 99;
        }
        if (count54 == 3) {
            totalup = totalup + 119;
        }
        if (count54 == 4) {
            totalup = totalup + 139;
        }
        if (count58 == 1) {
            totalup = totalup + 99;
        }
        if (count58 == 2) {
            totalup = totalup + 129;
        }
        if (count58 == 3) {
            totalup = totalup + 159;
        }
        if (count58 == 4) {
            totalup = totalup + 189;
        }


        if (count63 == 1) {
            totalup = totalup + 79;
        }
        if (count63 == 2) {
            totalup = totalup + 89;
        }
        if (count63 == 3) {
            totalup = totalup + 99;
        }
        if (count63 == 4) {
            totalup = totalup + 109;
        }
        if (count64 == 1) {
            totalup = totalup + 89;
        }
        if (count64 == 2) {
            totalup = totalup + 109;
        }
        if (count64 == 3) {
            totalup = totalup + 129;
        }
        if (count64 == 4) {
            totalup = totalup + 149;
        }
        if (count68 == 1) {
            totalup = totalup + 109;
        }
        if (count68 == 2) {
            totalup = totalup + 139;
        }
        if (count68 == 3) {
            totalup = totalup + 169;
        }
        if (count68 == 4) {
            totalup = totalup + 199;
        }

        if (count73 == 1) {
            totalup = totalup + 89;
        }
        if (count73 == 2) {
            totalup = totalup + 99;
        }
        if (count73 == 3) {
            totalup = totalup + 109;
        }
        if (count73 == 4) {
            totalup = totalup + 119;
        }
        if (count74 == 1) {
            totalup = totalup + 99;
        }
        if (count74 == 2) {
            totalup = totalup + 119;
        }
        if (count74 == 3) {
            totalup = totalup + 139;
        }
        if (count74 == 4) {
            totalup = totalup + 159;
        }
        if (count78 == 1) {
            totalup = totalup + 119;
        }
        if (count78 == 2) {
            totalup = totalup + 149;
        }
        if (count78 == 3) {
            totalup = totalup + 179;
        }
        if (count78 == 4) {
            totalup = totalup + 209;
        }

        if (count83 == 1) {
            totalup = totalup + 99;
        }
        if (count83 == 2) {
            totalup = totalup + 109;
        }
        if (count83 == 3) {
            totalup = totalup + 119;
        }
        if (count83 == 4) {
            totalup = totalup + 129;
        }
        if (count84 == 1) {
            totalup = totalup + 109;
        }
        if (count84 == 2) {
            totalup = totalup + 129;
        }
        if (count84 == 3) {
            totalup = totalup + 149;
        }
        if (count84 == 4) {
            totalup = totalup + 169;
        }
        if (count88 == 1) {
            totalup = totalup + 129;
        }
        if (count88 == 2) {
            totalup = totalup + 159;
        }
        if (count88 == 3) {
            totalup = totalup + 189;
        }
        if (count88 == 4) {
            totalup = totalup + 219;
        }

        if (count93 == 1) {
            totalup = totalup + 109;
        }
        if (count93 == 2) {
            totalup = totalup + 119;
        }
        if (count93 == 3) {
            totalup = totalup + 129;
        }
        if (count93 == 4) {
            totalup = totalup + 139;
        }
        if (count94 == 1) {
            totalup = totalup + 119;
        }
        if (count94 == 2) {
            totalup = totalup + 139;
        }
        if (count94 == 3) {
            totalup = totalup + 159;
        }
        if (count94 == 4) {
            totalup = totalup + 179;
        }
        if (count98 == 1) {
            totalup = totalup + 139;
        }
        if (count98 == 2) {
            totalup = totalup + 169;
        }
        if (count98 == 3) {
            totalup = totalup + 199;
        }
        if (count98 == 4) {
            totalup = totalup + 229;
        }


        if (count103 == 1) {
            totalup = totalup + count103 * 119;
        }
        if (count103 == 2) {
            totalup = totalup + count103 * 119;
        }
        if (count103 == 3) {
            totalup = totalup + count103 * 119;
        }
        if (count103 == 4) {
            totalup = totalup + count103 * 119;
        }
        if (count104 == 1) {
            totalup = totalup + count104 * 139;
        }
        if (count104 == 2) {
            totalup = totalup + count104 * 139;
        }
        if (count104 == 3) {
            totalup = totalup + count104 * 139;
        }
        if (count104 == 4) {
            totalup = totalup + count104 * 139;
        }
        if (count108 == 1) {
            totalup = totalup + count108 * 169;
        }
        if (count108 == 2) {
            totalup = totalup + count108 * 169;
        }
        if (count108 == 3) {
            totalup = totalup + count108 * 169;
        }
        if (count108 == 4) {
            totalup = totalup + count108 * 169;
        }
        } else {
            totalup = 0;
        }
        Log.d(TAG, "calculatepricevarsity1: "+totalup);

        Intent intent=getIntent();
        int howmuch=intent.getIntExtra(NUM,0);

        if(howmuch>0) {

            Log.d(TAG, "calculatepricevarsity: " + howmuch);
            total = 119 * howmuch;
        }
        else {totalup=0;}
        t1.setText(valueOf(totalup ));

        return 0;
    }

}
