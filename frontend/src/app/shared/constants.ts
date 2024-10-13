import { isDevMode } from '@angular/core';

export function backendURL(): string {
  return isDevMode() ? "http://localhost:8080" : "https://" + window.location.hostname + ":8080" + "/api";
}
