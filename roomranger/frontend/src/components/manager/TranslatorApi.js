import React, { useEffect, useState } from 'react';
import axios from "axios";
import './Translator.css';


export default function TranslatorApi() {
    const languageOptions = [
        { value: 'null', label: 'Choose language'},
        { value: 'af', label: 'Afrikaans' },
        { value: 'sq', label: 'Albanian' },
        { value: 'am', label: 'Amharic' },
        { value: 'ar', label: 'Arabic' },
        { value: 'hy', label: 'Armenian' },
        { value: 'az', label: 'Azerbaijani' },
        { value: 'eu', label: 'Basque' },
        { value: 'be', label: 'Belarusian' },
        { value: 'bn', label: 'Bengali' },
        { value: 'bs', label: 'Bosnian' },
        { value: 'bg', label: 'Bulgarian' },
        { value: 'ca', label: 'Catalan' },
        { value: 'ceb', label: 'Cebuano' },
        { value: 'ny', label: 'Chichewa' },
        { value: 'zh', label: 'Chinese (Simplified)' },
        { value: 'zh-TW', label: 'Chinese (Traditional)' },
        { value: 'co', label: 'Corsican' },
        { value: 'hr', label: 'Croatian' },
        { value: 'cs', label: 'Czech' },
        { value: 'da', label: 'Danish' },
        { value: 'nl', label: 'Dutch' },
        { value: 'en', label: 'English' },
        { value: 'eo', label: 'Esperanto' },
        { value: 'et', label: 'Estonian' },
        { value: 'tl', label: 'Filipino' },
        { value: 'fi', label: 'Finnish' },
        { value: 'fr', label: 'French' },
        { value: 'fy', label: 'Frisian' },
        { value: 'gl', label: 'Galician' },
        { value: 'ka', label: 'Georgian' },
        { value: 'de', label: 'German' },
        { value: 'el', label: 'Greek' },
        { value: 'gu', label: 'Gujarati' },
        { value: 'ht', label: 'Haitian Creole' },
        { value: 'ha', label: 'Hausa' },
        { value: 'haw', label: 'Hawaiian' },
        { value: 'iw', label: 'Hebrew' },
        { value: 'hi', label: 'Hindi' },
        { value: 'hmn', label: 'Hmong' },
        { value: 'hu', label: 'Hungarian' },
        { value: 'is', label: 'Icelandic' },
        { value: 'ig', label: 'Igbo' },
        { value: 'id', label: 'Indonesian' },
        { value: 'ga', label: 'Irish' },
        { value: 'it', label: 'Italian' },
        { value: 'ja', label: 'Japanese' },
        { value: 'jv', label: 'Javanese' },
        { value: 'kn', label: 'Kannada' },
        { value: 'kk', label: 'Kazakh' },
        { value: 'km', label: 'Khmer' },
        { value: 'rw', label: 'Kinyarwanda' },
        { value: 'ko', label: 'Korean' },
        { value: 'ku', label: 'Kurdish (Kurmanji)' },
        { value: 'ky', label: 'Kyrgyz' },
        { value: 'lo', label: 'Lao' },
        { value: 'la', label: 'Latin' },
        { value: 'lv', label: 'Latvian' },
        { value: 'lt', label: 'Lithuanian' },
        { value: 'lb', label: 'Luxembourgish' },
        { value: 'mk', label: 'Macedonian' },
        { value: 'mg', label: 'Malagasy' },
        { value: 'ms', label: 'Malay' },
        { value: 'ml', label: 'Malayalam' },
        { value: 'mt', label: 'Maltese' },
        { value: 'mi', label: 'Maori' },
        { value: 'mr', label: 'Marathi' },
        { value: 'mn', label: 'Mongolian' },
        { value: 'my', label: 'Myanmar (Burmese)' },
        { value: 'ne', label: 'Nepali' },
        { value: 'no', label: 'Norwegian' },
        { value: 'or', label: 'Odia (Oriya)' },
        { value: 'ps', label: 'Pashto' },
        { value: 'fa', label: 'Persian' },
        { value: 'pl', label: 'Polish' },
        { value: 'pt', label: 'Portuguese' },
        { value: 'pa', label: 'Punjabi' },
        { value: 'ro', label: 'Romanian' },
        { value: 'ru', label: 'Russian' },
        { value: 'sm', label: 'Samoan' },
        { value: 'gd', label: 'Scots Gaelic' },
        { value: 'sr', label: 'Serbian' },
        { value: 'st', label: 'Sesotho' },
        { value: 'sn', label: 'Shona' },
        { value: 'sd', label: 'Sindhi' },
        { value: 'si', label: 'Sinhala' },
        { value: 'sk', label: 'Slovak' },
        { value: 'sl', label: 'Slovenian' },
        { value: 'so', label: 'Somali' },
        { value: 'es', label: 'Spanish' },
        { value: 'su', label: 'Sundanese' },
        { value: 'sw', label: 'Swahili' },
        { value: 'sv', label: 'Swedish' },
        { value: 'tg', label: 'Tajik' },
        { value: 'ta', label: 'Tamil' },
        { value: 'te', label: 'Telugu' },
        { value: 'th', label: 'Thai' },
        { value: 'tr', label: 'Turkish' },
        { value: 'uk', label: 'Ukrainian' },
        { value: 'ur', label: 'Urdu' },
        { value: 'ug', label: 'Uyghur' },
        { value: 'uz', label: 'Uzbek' },
        { value: 'vi', label: 'Vietnamese' },
        { value: 'cy', label: 'Welsh' },
        { value: 'xh', label: 'Xhosa' },
        { value: 'yi', label: 'Yiddish' },
        { value: 'yo', label: 'Yoruba' },
        { value: 'zu', label: 'Zulu' },
    ];


    const [text, setText] = useState('');
    const [value, setValue] = useState('');
    const [fromLanguage, setFromLanguage] = useState('');
    const [toLanguage, setToLanguage] = useState('');

    const fetchTranslation = async () => {
        const options = {
            method: 'POST',
            url: 'https://rapid-translate-multi-traduction.p.rapidapi.com/t',
            headers: {
                'content-type': 'application/json',
                'X-RapidAPI-Key': '2905f3f868msh28400c2a3d4c3a8p1eb20cjsn68f7778eabb0',
                'X-RapidAPI-Host': 'rapid-translate-multi-traduction.p.rapidapi.com'
            },
            data: {
                from: fromLanguage,
                to: toLanguage,
                q: [`${ text }`]
            }
        };
        try {
            const response = await axios.request(options);
            setValue(response.data[0])
            console.log(response.data[0]);
        } catch (error) {
            console.error(error);
        }
    };


    const handleTranslate = () => {
        fetchTranslation();
     };


     return (
        <div className="translator-container">
        <div className="input-container">
            <textarea className="form-control" rows="3" placeholder="Enter text to translate" onChange={(e) => setText(e.target.value)} />
        </div>
        <div className="language-container">
            <label htmlFor="fromLanguage">Translate from:</label>
            <select id="fromLanguage" value={fromLanguage} onChange={(e) => setFromLanguage(e.target.value)}>
                {languageOptions.map((lang) => (
                    <option key={lang.value} value={lang.value}>{lang.label}</option>
                ))}
            </select>
            <label htmlFor="toLanguage">Translate to:</label>
            <select id="toLanguage" value={toLanguage} onChange={(e) => setToLanguage(e.target.value)}>
                {languageOptions.map((lang) => (
                    <option key={lang.value} value={lang.value}>{lang.label}</option>
                ))}
            </select>
        </div>
        <div className="button-container">
            <button onClick={handleTranslate}>Translate</button>
        </div>
        <div className="result-container">
            <h1>{value}</h1>
        </div>
    </div>
);
}