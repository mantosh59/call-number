import { WebPlugin } from "@capacitor/core";
import { CallNumberResult } from "./definitions";
export declare class CallNumber extends WebPlugin {
    constructor();
    showFilePicker(_options?: {
        fileTypes?: string[];
    }): Promise<CallNumberResult>;
}
