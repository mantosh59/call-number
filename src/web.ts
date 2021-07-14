import { WebPlugin } from "@capacitor/core";
import { CallNumberPlugin, CallNumberResult } from "./definitions";

export class CallNumber extends WebPlugin implements CallNumberPlugin {
  constructor() {
    super({
      name: "CallNumber",
      platforms: ["web"],
    });
  }
  call(options?: { number?: string; }): Promise<CallNumberResult> {
    throw new Error("Method not implemented.");
  }

  showFilePicker(_options?: {
    fileTypes?: string[];
  }): Promise<CallNumberResult> {
    return Promise.reject("Method not implemented");
  }
}
