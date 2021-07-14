# Capacitor File Picker

## Description

Plugin to call a number.

## Installation

- `npm i capacitor-call-number`
## Usage

import { CallNumber } from 'capacitor-call-number';

await CallNumber.call({ number: '94374747474', bypassAppChooser: false });

bypassAppChooser = true (It will take to default dailer screen)
bypassAppChooser = false (It will directly call the number) 
