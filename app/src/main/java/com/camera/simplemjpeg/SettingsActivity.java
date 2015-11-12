package com.camera.simplemjpeg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView.BufferType;

public class SettingsActivity extends Activity {

    Button settings_done;

    Spinner resolution_spinner;
    EditText width_input;
    EditText height_input;

    EditText address1_input;
    EditText address2_input;
    EditText address3_input;
    EditText address4_input;
    EditText port_input;
    EditText command_input;
    EditText authUsername;
    EditText authPassword;

    Button address1_increment;
    Button address2_increment;
    Button address3_increment;
    Button address4_increment;

    Button address1_decrement;
    Button address2_decrement;
    Button address3_decrement;
    Button address4_decrement;

    RadioGroup port_group;
    RadioGroup command_group;

    int width = Const.width;
    int height = Const.height;

    int ip_ad1 = Const.ip_ad1;
    int ip_ad2 = Const.ip_ad2;
    int ip_ad3 = Const.ip_ad3;
    int ip_ad4 = Const.ip_ad4;
    int ip_port = Const.ip_port;

    String username = Const.username;
    String password = Const.password;

    String ip_command = Const.ip_command;

    private int addToByte(String byteValue, int defaultValue, int value) {
        int val = defaultValue;

        if (!"".equals(byteValue)) {
            val = Integer.parseInt(byteValue);
        }

        val += value;

        if (val < 0) {
            val = 0;
        } else if (val > 255) {
            val = 255;
        }

        return val;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Bundle extras = getIntent().getExtras();

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.resolution_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        resolution_spinner = (Spinner) findViewById(R.id.resolution_spinner);
        resolution_spinner.setAdapter(adapter);

        width_input = (EditText) findViewById(R.id.width_input);
        height_input = (EditText) findViewById(R.id.height_input);

        address1_input = (EditText) findViewById(R.id.address1_input);
        address2_input = (EditText) findViewById(R.id.address2_input);
        address3_input = (EditText) findViewById(R.id.address3_input);
        address4_input = (EditText) findViewById(R.id.address4_input);
        port_input = (EditText) findViewById(R.id.port_input);
        command_input = (EditText) findViewById(R.id.command_input);

        authPassword = (EditText) findViewById(R.id.authPassword);
        authUsername = (EditText) findViewById(R.id.authUsername);

        port_group = (RadioGroup) findViewById(R.id.port_radiogroup);
        command_group = (RadioGroup) findViewById(R.id.command_radiogroup);

        if (extras != null) {
            width = extras.getInt("width", width);
            height = extras.getInt("height", height);

            ip_ad1 = extras.getInt("ip_ad1", ip_ad1);
            ip_ad2 = extras.getInt("ip_ad2", ip_ad2);
            ip_ad3 = extras.getInt("ip_ad3", ip_ad3);
            ip_ad4 = extras.getInt("ip_ad4", ip_ad4);
            ip_port = extras.getInt("ip_port", ip_port);
            ip_command = extras.getString("ip_command");

            password = extras.getString("password");
            username = extras.getString("username");

            width_input.setText(String.valueOf(width));
            height_input.setText(String.valueOf(height));
            resolution_spinner.setSelection(adapter.getCount() - 1);

            address1_input.setText(String.valueOf(ip_ad1));
            address2_input.setText(String.valueOf(ip_ad2));
            address3_input.setText(String.valueOf(ip_ad3));
            address4_input.setText(String.valueOf(ip_ad4));
            port_input.setText(String.valueOf(ip_port));
            command_input.setText(ip_command);

            authPassword.setText(password);
            authUsername.setText(username);
        }

        resolution_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View viw, int arg2, long arg3) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                String[] items = item.split("x");
                width = Integer.valueOf(items[0]);
                height = Integer.valueOf(items[1]);
                width_input.setText(String.valueOf(width));
                height_input.setText(String.valueOf(height));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        address1_increment = (Button) findViewById(R.id.address1_increment);
        address1_increment.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad1 = addToByte(address1_input.getText().toString(), ip_ad1, 1);
                        address1_input.setText(String.valueOf(ip_ad1), BufferType.NORMAL);

                    }
                }
        );
        address2_increment = (Button) findViewById(R.id.address2_increment);
        address2_increment.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad2 = addToByte(address2_input.getText().toString(), ip_ad2, 1);
                        address2_input.setText(String.valueOf(ip_ad2), BufferType.NORMAL);

                    }
                }
        );
        address3_increment = (Button) findViewById(R.id.address3_increment);
        address3_increment.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad3 = addToByte(address3_input.getText().toString(), ip_ad3, 1);
                        address3_input.setText(String.valueOf(ip_ad3), BufferType.NORMAL);

                    }
                }
        );
        address4_increment = (Button) findViewById(R.id.address4_increment);
        address4_increment.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad4 = addToByte(address4_input.getText().toString(), ip_ad4, 1);
                        address4_input.setText(String.valueOf(ip_ad4), BufferType.NORMAL);

                    }
                }
        );

        address1_decrement = (Button) findViewById(R.id.address1_decrement);
        address1_decrement.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad1 = addToByte(address1_input.getText().toString(), ip_ad1, -1);
                        address1_input.setText(String.valueOf(ip_ad1), BufferType.NORMAL);

                    }
                }
        );

        address2_decrement = (Button) findViewById(R.id.address2_decrement);
        address2_decrement.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad2 = addToByte(address2_input.getText().toString(), ip_ad2, -1);
                        address2_input.setText(String.valueOf(ip_ad2), BufferType.NORMAL);

                    }
                }
        );
        address3_decrement = (Button) findViewById(R.id.address3_decrement);
        address3_decrement.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad3 = addToByte(address3_input.getText().toString(), ip_ad3, -1);
                        address3_input.setText(String.valueOf(ip_ad3), BufferType.NORMAL);

                    }
                }
        );
        address4_decrement = (Button) findViewById(R.id.address4_decrement);
        address4_decrement.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        ip_ad4 = addToByte(address4_input.getText().toString(), ip_ad4, -1);
                        address4_input.setText(String.valueOf(ip_ad4), BufferType.NORMAL);

                    }
                }
        );

        port_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.port_80) {
                    port_input.setText(getString(R.string.port_80));
                } else if (checkedId == R.id.port_8080) {
                    port_input.setText(getString(R.string.port_8080));
                }
            }
        });

        command_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.command_streaming) {
                    command_input.setText(getString(R.string.command_streaming));
                } else if (checkedId == R.id.command_videofeed) {
                    command_input.setText(getString(R.string.command_videofeed));
                }
            }
        });

        settings_done = (Button) findViewById(R.id.settings_done);
        settings_done.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        width = getInt(width_input, width);
                        height = getInt(height_input, height);
                        ip_ad1 = getInt(address1_input, ip_ad1);
                        ip_ad2 = getInt(address2_input, ip_ad2);
                        ip_ad3 = getInt(address3_input, ip_ad3);
                        ip_ad4 = getInt(address4_input, ip_ad4);
                        ip_port = getInt(port_input, ip_port);
                        ip_command = command_input.getText().toString();

                        username = authUsername.getText().toString();
                        password = authPassword.getText().toString();

                        Intent intent = new Intent();
                        intent.putExtra("width", width);
                        intent.putExtra("height", height);
                        intent.putExtra("ip_ad1", ip_ad1);
                        intent.putExtra("ip_ad2", ip_ad2);
                        intent.putExtra("ip_ad3", ip_ad3);
                        intent.putExtra("ip_ad4", ip_ad4);
                        intent.putExtra("ip_port", ip_port);
                        intent.putExtra("ip_command", ip_command);

                        intent.putExtra("password", password);
                        intent.putExtra("username", username);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
        );
    }

    private int getInt(EditText edit, int defaultValue) {
        String s = edit.getText().toString();

        if (!"".equals(s)) {
            return Integer.parseInt(s);
        }

        return defaultValue;
    }
}
