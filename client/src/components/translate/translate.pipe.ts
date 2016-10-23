import { Pipe, PipeTransform } from '@angular/core';
import { TranslationService } from './translate.service';

@Pipe({
    name: 'translate',
    pure: false,
})
export class TranslatePipe implements PipeTransform{
    constructor(private translate: TranslationService){        
    }
    transform(value: string, args: any[]):any{
        if(!value) return;
        return this.translate.instant(value);
    }
}