import { SearchEntry } from './searchEntry.interface';

export interface SearchResult {
    id: number;
    entry: SearchEntry;
    category: string;
}