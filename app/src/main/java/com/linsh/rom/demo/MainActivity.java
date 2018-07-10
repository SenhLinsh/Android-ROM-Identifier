package com.linsh.rom.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.linsh.rom.ROM;
import com.linsh.rom.ROMInfo;
import com.linsh.rom.RomIdentifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ROM rom = RomIdentifier.getRomType(this);
        ROMInfo info = RomIdentifier.getRomInfo(this);

        ((TextView) findViewById(R.id.tv_rom_type)).setText(String.format("ROM 类型:    %s", rom.toString()));
        ((TextView) findViewById(R.id.tv_rom_info)).setText(String.format("ROM 信息:    %s [版本: %s, 版本号: %s]", info.getRom(), info.getBaseVersion(), info.getVersion()));
    }

}
