import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Define an interface for the response structure
interface GeminiResponse {
  candidates: Array<{
    content: {
      parts: Array<{ text: string }>;
    };
    finishReason: string;
    index: number;
    safetyRatings: Array<{
      category: string;
      probability: string;
    }>;
  }>;
  usageMetadata: {
    promptTokenCount: number;
    candidatesTokenCount: number;
    totalTokenCount: number;
  };
}

@Injectable({
  providedIn: 'root'
})
export class GeminiService {
  private apiUrl = 'http://localhost:3333/prompt';

  constructor(private http: HttpClient) { }

  getResponse(prompt: string): Observable<GeminiResponse> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<GeminiResponse>(`${this.apiUrl}?prompt=${encodeURIComponent(prompt)}`, { headers });
  }
}
