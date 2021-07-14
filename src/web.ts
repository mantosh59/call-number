import { WebPlugin } from "@capacitor/core";
import { CallNumberResult } from "./definitions";

export class CallNumber extends WebPlugin {
  constructor() {
    super({
      name: "CallNumber",
      platforms: ["web"],
    });
  }

  showFilePicker(_options?: {
    fileTypes?: string[];
  }): Promise<CallNumberResult> {
    return Promise.reject("Method not implemented");
  }
}
