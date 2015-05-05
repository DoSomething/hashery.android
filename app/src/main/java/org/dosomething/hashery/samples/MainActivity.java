package org.dosomething.hashery.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.dosomething.hashery.Hashery;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mEncodeInput;
    private Button mEncodeButton;
    private TextView mEncodeResult;

    private Hashery mHashery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView viewMaxValue = (TextView)findViewById(R.id.maxvalue_text);

        mEncodeInput = (EditText)findViewById(R.id.encode_input);
        mEncodeButton = (Button)findViewById(R.id.encode_button);
        mEncodeResult = (TextView)findViewById(R.id.encode_result);

        mEncodeButton.setOnClickListener(this);

        // Adjectives
        String[] adjectives = {"abandoned","able","absolute","adorable","adventurous","academic","acceptable","acclaimed","accomplished","accurate","aching","acidic","acrobatic","babyish","bad","back","baggy","bare","barren","basic","beautiful","belated","beloved","gargantuan","gaseous","general","generous","gentle","genuine","giant","giddy","gigantic","obedient","obese","oblong","oily","oblong","obvious","occasional","palatable"};
        ArrayList<String> w0 = new ArrayList<String>(Arrays.asList(adjectives));

        // Colors
        String[] colors = {"Alizarin","Amaranth","Amber","Amethyst","Apricot","Aqua","Aquamarine","Asparagus","Auburn","Azure","Beige","Bistre","Black","Blue","Celadon","Cerise","Cerulean","Champagne","Charcoal","Lilac","Lime","Olive","Orange","Pink","Platinum","Plum","Powder","Pumpkin","Purple","Saffron","Salmon","Sangria","Sapphire","Scarlet","Tan","Tangerine","Taupe","Teal","Violet","Viridian"};
        ArrayList<String> w1 = new ArrayList<String>(Arrays.asList(colors));

        // Animals
        String[] animals = {"Angelfish","Ant","Anteater","Antelope","Avocet","Axolotl","Baboon","Badger","Balinese","Bandicoot","Barb","Barnacle","Barracuda","Bat","Beagle","Bear","Beaver","Beetle","Binturong","Bird","Chicken","Chihuahua","Chimpanzee","Chinchilla","Dolphin","Donkey","Dormouse","Dragonfly","Drever","Duck","Dugong","Dunker","Maltese","Manatee","Mandrill","Mole","Molly","Mongoose","Mongrel","Penguin"};
        ArrayList<String> w2 = new ArrayList<String>(Arrays.asList(animals));

        ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
        words.add(w0);
        words.add(w1);
        words.add(w2);

        mHashery = new Hashery(words);

        // Display max allowed value
        int maxval = mHashery.getMaxValue();
        viewMaxValue.setText("Max value: " + maxval);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.encode_button) {
            String input = mEncodeInput.getText().toString();
            int value = Integer.parseInt(input);

            if (value >= 0 && value <= mHashery.getMaxValue()) {
                String result = mHashery.encode(value);
                mEncodeResult.setText(result);
            }
            else {
                Toast.makeText(MainActivity.this, "Invalid code", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
