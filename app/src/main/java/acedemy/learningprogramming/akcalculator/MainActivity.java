package acedemy.learningprogramming.akcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayoperation;

    //variable to hold operands and type of calculation
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendindOperation = "=";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayoperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonEqual = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        Button clear =(Button)findViewById((R.id.clear));
        Button neg = (Button)findViewById(R.id.neg);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
                //operand2 = Double.parseDouble(newNumber.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);


        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();

                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue,op);
                }
                catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendindOperation = op;
                displayoperation.setText(pendindOperation);
            }
        };
        buttonDivide.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonMultiply.setOnClickListener(operationListener);
        buttonEqual.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);


        View.OnClickListener clearaListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand1 = null;
                operand2 = null;
                pendindOperation = "=";
                newNumber.setText("");
                result.setText("");
                displayoperation.setText("");
            }
        };
        clear.setOnClickListener(clearaListener);

        View.OnClickListener negListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String value=newNumber.getText().toString();
               if(value.length()==0)
               {
                   newNumber.setText("-");
               }
               else
               {
                   try{
                       Double doubleValue = Double.valueOf(value);

                       doubleValue*= -1;
                       newNumber.setText(doubleValue.toString());
                   }catch(NumberFormatException e)
                   {
                       newNumber.setText("");
                   }
               }
            }
        };
        neg.setOnClickListener(negListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION,pendindOperation);
        if(operand1!=null)
        {
            outState.putDouble(STATE_OPERAND1,operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendindOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayoperation.setText(pendindOperation);
    }

    private void performOperation(Double value, String operation) {
        if (operand1 == null) {
            operand1 = value;

        } else {
            operand2= value;
            if(pendindOperation.equals("="))
            {
                pendindOperation=operation;
            }
            switch (pendindOperation){
                case "=":
                    operand1=operand2;
                    break;
                case "/":
                    if(operand2==0)
                        operand1=0.0;
                    else
                        operand1/=operand2;
                    break;
                case "*":
                    operand1*=operand2;
                    break;
                case "-":
                    operand1-=operand2;
                    break;
                case "+":
                    operand1+=operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
