import { FC, ChangeEvent } from 'react'
import '../general.css'

interface SelectProps {
    handleOperation: (event: ChangeEvent<HTMLSelectElement>) => void
    operationSelected: string
}

export const Dropdown: FC<SelectProps> = ({ handleOperation, operationSelected }) => {

    return (
      <select className='general-dropdown' name="operations" onChange={handleOperation} value={operationSelected}>
        <option value="<">{'<'}</option>
        <option value="<=">{'<='}</option>
        <option value="==">{'=='}</option>
        <option value=">">{'>'}</option>
        <option value=">=">{'>='}</option>
      </select>
    );
  };