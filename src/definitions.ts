export interface CallNumberResult {
  message: string;
}

export interface CallNumberPlugin {
  call(options?: { number?: string; bypassAppChooser?: boolean }): Promise<CallNumberResult>;
}
