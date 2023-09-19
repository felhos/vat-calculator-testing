<a name="readme-top"></a>
# Test documentation

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#acceptance-criteria">Accepance criteria</a>
    </li>
    <li>
      <a href="#bug-report">Bug report</a>
      <ul>
        <li><a href="#acceptance-criteria">Acceptance criteria</a></li>
        <li><a href="#other-issues">Other issues</a></li>
      </ul>
    </li>
    <li><a href="#limits-of-automation">Limits of automation</a></li>
    <li><a href="#open-questions">Open questions</a></li>
    <li><a href="#other-notes">Other notes</a></li>
  </ol>
</details>

## Acceptance criteria

### User can select countries
Country selection works as intended.
### User can select VAT rate for the selected country
VAT rate selection works as intended.
### One of the following amounts are supported as an input: Net/Gross/VAT
Couldn't fully test it with automation, but manual testing shows it works as intended.
### Amounts can be entered with maximum 2 decimal digit precision
The results are rounded to maximum two decimal. The input however can include more as long as the total digits are less than 10 (including the decimal point). The input's decimals after the second does affect the calculation.
### Given all mandatory fields (country, vat rate, one of the amounts) are provided, the website will calculate and show the other 2 amounts which were not provided originally as an input value
Couldn't fully test it with automation, but it works as intended as long as the input is not too big.
### The API provides an error with meaningful error message, in case of negative input
The site does the calculations normally with negative inputs. There is a visual component: a pie chart to show the share of net price and VAT, and that pie chart is missing with a proper error message, when the input is negative.
[Note: calculating VAT on negative numbers can have meaningful use, as it occurs in accounting]
### The API provides an error with meaningful error message, in case of amount >999,999,999
You cannot enter bigger numbers than 999,999,999, but in case one of the other field goes over that limit, the display cuts the end of the number. The element still holds the correct value, but is incorrectly displayed. No error message is shown.
<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Bug report

### Acceptance criteria
- Negative and big inputs don't behave the way it is specified in the acceptance criteria.
- Inputs can be given with higher precision than two decimals.
### Other issues
- Non-numerical inputs can be given, if it doesn't start with a digit it shows NaN in the other field. If it starts with a digit it considers it a number (e.g. 123hf45k is considered 123 by the website).
- Croatian and Norwegian languages are able to be selected, but aren't available.
- In the Czech version the font is broken. The text is still legible but looks unappealing.
- The website's accessibility is poor for users with vision impairment or low vision (checked by  Chrome extensions axe DevTools - Web Accessibility Testing)
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Open questions
- Do negative inputs have to be treated as incorrect inputs?
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Limits of automation
The following test cases cannot be evaluated with automated tests alone and needs manual testing:
- Examining the responsivity of the site, whether the layout breaks when displayed in lower resolution
- Other visual glitches, like broken fonts or misaligned elements should be checked for manually as well
- Testing the accessibility of the site. There are tools, that can help with it (e.g. checking that all interactible elements have labels, checking color contrasts), but evaluating the user experience needs manual testing
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Other notes

- The tests were run only in Chrome on Windows 11 operating system. Should be tested with other browsers and OSes.
- The project is not fully functional, there are issues in two tests, that I couldn't resolve
<p align="right">(<a href="#readme-top">back to top</a>)</p>